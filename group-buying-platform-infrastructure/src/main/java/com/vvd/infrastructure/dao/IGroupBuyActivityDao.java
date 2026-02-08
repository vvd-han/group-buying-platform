package com.vvd.infrastructure.dao;

import com.vvd.infrastructure.dao.po.GroupBuyActivity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author vvd
 * @description
 * @create 2026-02-04 22:00
 */
@Mapper
public interface IGroupBuyActivityDao {

    List<GroupBuyActivity> queryGroupBuyActivityList();

    GroupBuyActivity queryValidGroupBuyActivity(GroupBuyActivity groupBuyActivityReq);

}
