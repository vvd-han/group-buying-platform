package com.vvd.infrastructure.dao;

import com.vvd.infrastructure.dao.po.Sku;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author vvd
 * @description
 * @create 2026-02-06 16:14
 */
@Mapper
public interface ISkuDao {

    Sku querySkuByGoodsId(String goodsId);

}
