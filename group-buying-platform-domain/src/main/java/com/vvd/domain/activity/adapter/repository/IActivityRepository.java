package com.vvd.domain.activity.adapter.repository;

import com.vvd.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.vvd.domain.activity.model.valobj.SkuVO;

/**
 * @author vvd
 * @description
 * @create 2026-02-05 23:01
 */
public interface IActivityRepository {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(String source, String channel);

    SkuVO querySkuByGoodsId(String goodsId);

}
