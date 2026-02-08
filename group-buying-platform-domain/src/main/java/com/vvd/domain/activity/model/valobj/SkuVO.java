package com.vvd.domain.activity.model.valobj;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author vvd
 * @description
 * @create 2026-02-06 16:49
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuVO {

    /** 商品ID */
    private String goodsId;
    /** 商品名称 */
    private String goodsName;
    /** 原始价格 */
    private BigDecimal originalPrice;

}
