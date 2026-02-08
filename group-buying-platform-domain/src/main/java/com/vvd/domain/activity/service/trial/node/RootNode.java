package com.vvd.domain.activity.service.trial.node;

import com.alibaba.fastjson.JSON;
import com.vvd.domain.activity.model.entity.MarketProductEntity;
import com.vvd.domain.activity.model.entity.TrialBalanceEntity;
import com.vvd.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.vvd.domain.activity.service.trial.factory.DefaultActivityStrategyFactory.DynamicContext;
import com.vvd.types.design.framework.tree.StrategyHandle;
import com.vvd.types.enums.ResponseCode;
import com.vvd.types.exception.AppException;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author vvd
 * @description
 * @create 2026-02-05 16:45
 */
@Slf4j
@Service
public class RootNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> {

    @Resource
    private SwitchRoot switchRoot;

    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DynamicContext dynamicContext)
        throws Exception {
        log.info("拼团商品查询试算服务-RootNode userId:{} requestParameter:{}", requestParameter.getUserId(),
            JSON.toJSONString(requestParameter));
        if (StringUtils.isBlank(requestParameter.getUserId()) ||
            StringUtils.isBlank(requestParameter.getGoodsId()) ||
            StringUtils.isBlank(requestParameter.getSource()) ||
            StringUtils.isBlank(requestParameter.getChannel())
        ) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandle<MarketProductEntity, DynamicContext, TrialBalanceEntity> get(
        MarketProductEntity requestParameter, DynamicContext dynamicContext) throws Exception {
        return switchRoot;
    }

}
