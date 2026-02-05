package com.vvd.types.design.framework.tree;

/**
 * @author vvd
 * @description 策略映射器
 * @create 2026-02-05 11:46
 */
public interface StrategyMapper<T, D, R> {

    StrategyHandle<T, D, R> get(T requestParameter, D dynamicContext) throws Exception;

}
