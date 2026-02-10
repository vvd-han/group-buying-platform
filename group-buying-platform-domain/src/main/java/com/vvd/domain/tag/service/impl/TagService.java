package com.vvd.domain.tag.service.impl;

import com.vvd.domain.tag.adapter.repository.ITagReposiyory;
import com.vvd.domain.tag.model.entity.CrowdTagsJobEntity;
import com.vvd.domain.tag.service.ITagService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author vvd-han
 * @description
 * @create 2026-02-09 22:20
 */
@Slf4j
@Service
public class TagService implements ITagService {
    
    @Resource
    private ITagReposiyory reposiyory;
    
    /**
     * 执行人群标签批次任务
     *
     * @param tagId   标签ID，用于标识特定的人群标签
     * @param batchId 批次ID，用于标识当前执行的任务批次
     */
    @Override
    public void execTagBatchJob(String tagId, String batchId) {
        log.info("人群标签批次任务 tagId:{} batchId:{}", tagId, batchId);
        
        // 查询人群标签任务实体信息
        CrowdTagsJobEntity crowdTagsJobEntity = reposiyory.queryCrowTagsJobEntity(tagId, batchId);
        
        // 采集用户数据
        List<String> userIdList = new ArrayList<String>() {
            {
                add("vvd");
                add("han");
            }
        };
        
        // 遍历用户列表，将用户ID添加到人群标签中
        for (String userId : userIdList) {
            reposiyory.addCrowdTagsUserId(tagId, userId);
        }
        
        // 更新人群标签的统计信息
        reposiyory.updateCrowdTagsStatistics(tagId, userIdList.size());
    }
    
}
