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
@Service("N")
public class NCalculateService extends AbstractDiscountCalculateService {
    
    /**
     * 执行N元购类型的折扣计算
     *
     * @param originalPrice    原始商品价格
     * @param groupBuyDiscount 团购折扣配置信息，其中marketExpr字段存储N元购的目标价格
     * @return N元购的固定价格，直接返回marketExpr中配置的价格值
     */
    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyDiscount groupBuyDiscount) {
        String marketExpr = groupBuyDiscount.getMarketExpr();
        return new BigDecimal(marketExpr);
    }
    
}
