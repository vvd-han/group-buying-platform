package com.vvd.domain.activity.service.discount.Impl;

import com.vvd.domain.activity.model.valobj.GroupBuyActivityDiscountVO.GroupBuyDiscount;
import com.vvd.domain.activity.service.discount.AbstractDiscountCalculateService;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author vvd-han
 * @description
 * @create 2026-02-08 22:42
 */
@Slf4j
@Service("ZJ")
public class ZJCalculateService extends AbstractDiscountCalculateService {
    
    /**
     * 执行直减类型的折扣计算
     *
     * @param originalPrice    原始商品价格
     * @param groupBuyDiscount 团购折扣配置信息，其中marketExpr字段存储直减金额
     * @return 计算后的折扣价格，如果计算结果≤0则返回0.01
     */
    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyDiscount groupBuyDiscount) {
        // 获取直减金额并计算折扣后价格
        String marketExpr = groupBuyDiscount.getMarketExpr();
        BigDecimal deductionPrice = originalPrice.subtract(new BigDecimal(marketExpr));
        
        // 确保最终价格不低于0.01元（避免出现负数或零元商品）
        if (deductionPrice.compareTo(BigDecimal.ZERO) <= 0) {
            return new BigDecimal("0.01");
        }
        
        return deductionPrice;
    }
    
}
