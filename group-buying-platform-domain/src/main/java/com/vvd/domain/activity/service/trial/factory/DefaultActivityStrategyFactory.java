package com.vvd.domain.activity.service.trial.factory;

import com.vvd.domain.activity.model.entity.MarketProductEntity;
import com.vvd.domain.activity.model.entity.TrialBalanceEntity;
import com.vvd.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.vvd.domain.activity.model.valobj.SkuVO;
import com.vvd.domain.activity.service.trial.node.RootNode;
import com.vvd.types.design.framework.tree.StrategyHandle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author vvd
 * @description
 * @create 2026-02-05 16:42
 */
@Slf4j
@Service
public class DefaultActivityStrategyFactory {
    
    private final RootNode rootNode;
    
    public DefaultActivityStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }
    
    public StrategyHandle<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> strategyHandle() {
        return rootNode;
    }
    
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        
        private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;
        private SkuVO skuVO;
        
    }
    
}
