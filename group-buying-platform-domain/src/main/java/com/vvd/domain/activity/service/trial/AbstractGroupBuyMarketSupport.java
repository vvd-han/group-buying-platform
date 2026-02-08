package com.vvd.domain.activity.service.trial;

import com.vvd.domain.activity.adapter.repository.IActivityRepository;
import com.vvd.types.design.framework.tree.AbstractMultiThreadStrategyRouter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import javax.annotation.Resource;

/**
 * @author vvd
 * @description
 * @create 2026-02-05 16:34
 */
public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends
    AbstractMultiThreadStrategyRouter<MarketProductEntity, DynamicContext, TrialBalanceEntity> {
    
    protected long timeout = 500;
    
    @Resource
    protected IActivityRepository repository;
    
    @Override
    protected void multiThread(MarketProductEntity requestParameter,
        DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        // 缺省的方法
    }
    
}
