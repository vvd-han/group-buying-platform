package com.vvd.domain.activity.service.trial.node;

import com.vvd.domain.activity.model.entity.MarketProductEntity;
import com.vvd.domain.activity.model.entity.TrialBalanceEntity;
import com.vvd.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.vvd.domain.activity.model.valobj.GroupBuyActivityDiscountVO.GroupBuyDiscount;
import com.vvd.domain.activity.model.valobj.SkuVO;
import com.vvd.domain.activity.service.discount.IDiscountCalculateService;
import com.vvd.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.vvd.domain.activity.service.trial.factory.DefaultActivityStrategyFactory.DynamicContext;
import com.vvd.domain.activity.service.trial.thread.QueryGroupBuyActivityDiscountVOThreadTask;
import com.vvd.domain.activity.service.trial.thread.QuerySkuVOFromDBThreadTask;
import com.vvd.types.design.framework.tree.StrategyHandle;
import com.vvd.types.enums.ResponseCode;
import com.vvd.types.exception.AppException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author vvd
 * @description
 * @create 2026-02-05 16:51
 */
@Slf4j
@Service
public class MarketNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> {
    
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;
    
    @Resource
    private EndNode endNode;
    
    @Resource
    private Map<String, IDiscountCalculateService> discountCalculateServiceMap;
    
    @Override
    protected void multiThread(MarketProductEntity requestParameter, DynamicContext dynamicContext)
        throws ExecutionException, InterruptedException, TimeoutException {
        
        // 异步查询活动配置
        QueryGroupBuyActivityDiscountVOThreadTask queryGroupBuyActivityDiscountVOThreadTask = new QueryGroupBuyActivityDiscountVOThreadTask(
            requestParameter.getSource(), requestParameter.getChannel(), repository);
        FutureTask<GroupBuyActivityDiscountVO> groupBuyActivityDiscountVOFutureTask = new FutureTask<>(
            queryGroupBuyActivityDiscountVOThreadTask);
        threadPoolExecutor.execute(groupBuyActivityDiscountVOFutureTask);
        
        // 异步查询商品信息 - 在实际生产中，商品有同步库或者调用接口查询。这里暂时使用DB方式查询。
        QuerySkuVOFromDBThreadTask querySkuVOFromDBThreadTask = new QuerySkuVOFromDBThreadTask(
            requestParameter.getGoodsId(), repository);
        FutureTask<SkuVO> skuVOFutureTask = new FutureTask<>(querySkuVOFromDBThreadTask);
        threadPoolExecutor.execute(skuVOFutureTask);
        threadPoolExecutor.execute(skuVOFutureTask);
        
        // 写入上下文 - 对于一些复杂场景，获取数据的操作，有时候会在下N个节点获取，这样前置查询数据，可以提高接口响应效率
        dynamicContext.setGroupBuyActivityDiscountVO(
            groupBuyActivityDiscountVOFutureTask.get(timeout, TimeUnit.MILLISECONDS));
        dynamicContext.setSkuVO(skuVOFutureTask.get(timeout, TimeUnit.MILLISECONDS));
        
    }
    
    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter,
        DynamicContext dynamicContext) throws Exception {
        
        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = dynamicContext.getGroupBuyActivityDiscountVO();
        GroupBuyDiscount groupBuyDiscount = groupBuyActivityDiscountVO.getGroupBuyDiscount();
        SkuVO skuVO = dynamicContext.getSkuVO();
        
        IDiscountCalculateService discountCalculateService = discountCalculateServiceMap.get(
            groupBuyDiscount.getMarketPlan());
        
        if (null == discountCalculateService) {
            throw new AppException(ResponseCode.E0001.getInfo());
        }
        
        BigDecimal deductionPrice = discountCalculateService.calculate(requestParameter.getUserId(),
            skuVO.getOriginalPrice(), groupBuyDiscount);
        
        dynamicContext.setDeductionPrice(deductionPrice);
        
        return router(requestParameter, dynamicContext);
    }
    
    @Override
    public StrategyHandle<MarketProductEntity, DynamicContext, TrialBalanceEntity> get(
        MarketProductEntity requestParameter, DynamicContext dynamicContext) throws Exception {
        return endNode;
    }
    
}
