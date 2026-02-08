package com.vvd.domain.activity.service.discount;

import com.vvd.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import java.math.BigDecimal;

/**
 * @author vvd-han
 * @description
 * @create 2026-02-08 22:16
 */
public interface IDiscountCalculateService {
    
    BigDecimal calculate(String userId, BigDecimal originalPrice,
        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);
    
}
