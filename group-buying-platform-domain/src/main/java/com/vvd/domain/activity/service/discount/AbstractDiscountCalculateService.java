package com.vvd.domain.activity.service.discount;

import com.vvd.domain.activity.model.valobj.DiscountTypeEnum;
import com.vvd.domain.activity.model.valobj.GroupBuyActivityDiscountVO.GroupBuyDiscount;
import java.math.BigDecimal;

/**
 * @author vvd-han
 * @description
 * @create 2026-02-08 22:30
 */
public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService {
    
    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyDiscount groupBuyDiscount) {
        // 如果是人群标签类型的折扣，需要先验证用户是否在目标人群中
        if (DiscountTypeEnum.TAG.equals(groupBuyDiscount.getDiscountType())) {
            boolean isCrowRange = filterTagId(userId, groupBuyDiscount.getTagId());
            if (!isCrowRange) {
                return originalPrice;
            }
        }
        // 执行具体的折扣计算逻辑
        return doCalculate(originalPrice, groupBuyDiscount);
    }
    
    
    private boolean filterTagId(String userId, String tagId) {
        return true;
    }
    
    protected abstract BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyDiscount groupBuyDiscount);
    
}
