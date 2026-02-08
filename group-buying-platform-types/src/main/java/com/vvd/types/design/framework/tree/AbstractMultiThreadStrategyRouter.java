package com.vvd.types.design.framework.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vvd
 * @description
 * @create 2026-02-05 22:34
 */
public abstract class AbstractMultiThreadStrategyRouter<T, D, R> implements StrategyMapper<T, D, R>,
    StrategyHandle<T, D, R> {

    @Getter
    @Setter
    protected StrategyHandle<T, D, R> defaultStrategyHandle = StrategyHandle.DEFAULT;

    public R router(T requestParameter, D dynamicContext) throws Exception {
        StrategyHandle<T, D, R> strategyHandle = get(requestParameter, dynamicContext);
        if (null != strategyHandle) {
            return strategyHandle.apply(requestParameter, dynamicContext);
        }
        return defaultStrategyHandle.apply(requestParameter, dynamicContext);
    }

    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {
        multiThread(requestParameter, dynamicContext);
        return doApply(requestParameter, dynamicContext);
    }

    protected abstract R doApply(T requestParameter, D dynamicContext) throws Exception;

    protected abstract void multiThread(T requestParameter, D dynamicContext) throws Exception;

}
