package com.vvd.infrastructure.dao;

import com.vvd.infrastructure.dao.po.CrowdTags;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author vvd-han
 * @description
 * @create 2026-02-09 21:59
 */
@Mapper
public interface ICrowdTagsDao {
    
    void updateCrowdTagsStatistics(CrowdTags crowdTagsReq);
    
}
