package com.vvd.domain.activity.service.trial.node;

import com.vvd.domain.activity.model.entity.MarketProductEntity;
import com.vvd.domain.activity.model.entity.TrialBalanceEntity;
import com.vvd.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.vvd.domain.activity.model.valobj.SkuVO;
import com.vvd.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.vvd.domain.activity.service.trial.factory.DefaultActivityStrategyFactory.DynamicContext;
import com.vvd.types.design.framework.tree.StrategyHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author vvd
 * @description
 * @create 2026-02-05 16:52
 */
@Slf4j
@Service
public class EndNode extends
    AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> {
    
    
    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter, DynamicContext dynamicContext)
        throws Exception {
        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = dynamicContext.getGroupBuyActivityDiscountVO();
        SkuVO skuVO = dynamicContext.getSkuVO();
        
        // 返回空结果
        return TrialBalanceEntity.builder()
            .goodsId(skuVO.getGoodsId())
            .goodsName(skuVO.getGoodsName())
            .originalPrice(skuVO.getOriginalPrice())
            .deductionPrice(dynamicContext.getDeductionPrice())
            .targetCount(groupBuyActivityDiscountVO.getTarget())
            .startTime(groupBuyActivityDiscountVO.getStartTime())
            .endTime(groupBuyActivityDiscountVO.getEndTime())
            .isVisible(false)
            .isEnable(false)
            .build();
        
    }
    
    @Override
    public StrategyHandle<MarketProductEntity, DynamicContext, TrialBalanceEntity> get(
        MarketProductEntity requestParameter, DynamicContext dynamicContext) throws Exception {
        return defaultStrategyHandle;
    }
    
}
