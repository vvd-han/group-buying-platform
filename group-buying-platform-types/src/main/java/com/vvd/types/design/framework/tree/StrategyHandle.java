package com.vvd.types.design.framework.tree;

/**
 * @author vvd
 * @description 策略处理器
 * @create 2026-02-05 11:47
 */
public interface StrategyHandle<T, D, R> {

    StrategyHandle DEFAULT = (T, D) -> null;

    R apply(T requestParameter, D dynamicContext) throws Exception;

}
