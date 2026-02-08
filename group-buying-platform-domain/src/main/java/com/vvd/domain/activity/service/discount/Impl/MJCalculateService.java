package com.vvd.domain.activity.service.discount.Impl;

import com.vvd.domain.activity.model.valobj.GroupBuyActivityDiscountVO.GroupBuyDiscount;
import com.vvd.domain.activity.service.discount.AbstractDiscountCalculateService;
import com.vvd.types.common.Constants;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author vvd-han
 * @description
 * @create 2026-02-08 22:37
 */
@Slf4j
@Service("MJ")
public class MJCalculateService extends AbstractDiscountCalculateService {
    
    /**
     * 执行满减类型的折扣计算
     *
     * @param originalPrice    原始商品价格
     * @param groupBuyDiscount 团购折扣配置信息，其中marketExpr字段存储满减规则"x,y"格式
     * @return 计算后的折扣价格，如果未达到满减条件则返回原价，如果计算结果≤0则返回0.01
     */
    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyDiscount groupBuyDiscount) {
        // 解析满减规则表达式，格式为"x,y"，其中x为满减门槛，y为减免金额
        String marketExpr = groupBuyDiscount.getMarketExpr();
        String[] split = marketExpr.split(Constants.SPLIT);
        BigDecimal x = new BigDecimal(split[0]);
        BigDecimal y = new BigDecimal(split[1]);
        
        // 检查是否达到满减门槛，未达到则返回原价
        if (originalPrice.compareTo(x) < 0) {
            return originalPrice;
        }
        
        // 计算满减后的价格
        BigDecimal deductionPrice = originalPrice.subtract(y);
        
        // 确保最终价格不低于0.01元（避免出现负数或零元商品）
        if (deductionPrice.compareTo(BigDecimal.ZERO) <= 0) {
            return new BigDecimal("0.01");
        }
        
        return deductionPrice;
    }
    
}
