package com.vvd.domain.tag.adapter.repository;

import com.vvd.domain.tag.model.entity.CrowdTagsJobEntity;

/**
 * @author vvd-han
 * @description
 * @create 2026-02-09 22:21
 */
public interface ITagReposiyory {
    
    CrowdTagsJobEntity queryCrowTagsJobEntity(String tagId, String batchId);
    
    void addCrowdTagsUserId(String tagId, String userId);
    
    void updateCrowdTagsStatistics(String tagId, int size);
    
}
