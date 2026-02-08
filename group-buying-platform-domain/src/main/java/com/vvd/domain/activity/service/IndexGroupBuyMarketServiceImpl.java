package com.vvd.domain.activity.service;

import com.vvd.domain.activity.model.entity.MarketProductEntity;
import com.vvd.domain.activity.model.entity.TrialBalanceEntity;
import com.vvd.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.vvd.domain.activity.service.trial.factory.DefaultActivityStrategyFactory.DynamicContext;
import com.vvd.types.design.framework.tree.StrategyHandle;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author vvd
 * @description
 * @create 2026-02-05 16:57
 */
@Service
public class IndexGroupBuyMarketServiceImpl implements IIndexGroupBuyMarketService {

    @Resource
    private DefaultActivityStrategyFactory defaultActivityStrategyFactory;

    @Override
    public TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity)
        throws Exception {

        StrategyHandle<MarketProductEntity, DynamicContext, TrialBalanceEntity> strategyHandle = defaultActivityStrategyFactory.strategyHandle();

        TrialBalanceEntity trialBalanceEntity = strategyHandle.apply(marketProductEntity,
            new DefaultActivityStrategyFactory.DynamicContext());

        return trialBalanceEntity;
    }

}
