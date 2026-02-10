package com.vvd.infrastructure.dao;

import com.vvd.infrastructure.dao.po.CrowdTagsDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author vvd-han
 * @description
 * @create 2026-02-09 21:59
 */
@Mapper
public interface ICrowdTagsDetailDao {
    
    void addCrowdTagsUserId(CrowdTagsDetail crowdTagsDetailReq);
    
}
