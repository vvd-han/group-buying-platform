package com.vvd.infrastructure.dao;

import com.vvd.infrastructure.dao.po.GroupBuyDiscount;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author vvd
 * @description
 * @create 2026-02-04 22:01
 */
@Mapper
public interface IGroupBuyDiscountDao {
    
    List<GroupBuyDiscount> queryGroupBuyDiscountList();
    
    GroupBuyDiscount queryGroupBuyActivityByDiscountId(String discountId);
    
}
