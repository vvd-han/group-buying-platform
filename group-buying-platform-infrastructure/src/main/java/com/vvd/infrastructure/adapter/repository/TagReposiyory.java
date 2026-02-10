package com.vvd.infrastructure.adapter.repository;

import com.vvd.domain.tag.adapter.repository.ITagReposiyory;
import com.vvd.domain.tag.model.entity.CrowdTagsJobEntity;
import com.vvd.infrastructure.dao.ICrowdTagsDao;
import com.vvd.infrastructure.dao.ICrowdTagsDetailDao;
import com.vvd.infrastructure.dao.ICrowdTagsJobDao;
import com.vvd.infrastructure.dao.po.CrowdTags;
import com.vvd.infrastructure.dao.po.CrowdTagsDetail;
import com.vvd.infrastructure.dao.po.CrowdTagsJob;
import com.vvd.infrastructure.redis.IRedisService;
import javax.annotation.Resource;
import org.redisson.api.RBitSet;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

/**
 * @author vvd-han
 * @description
 * @create 2026-02-09 22:39
 */
@Repository
public class TagReposiyory implements ITagReposiyory {
    
    @Resource
    private ICrowdTagsDao crowdTagsDao;
    
    @Resource
    private ICrowdTagsDetailDao crowdTagsDetailDao;
    
    @Resource
    private ICrowdTagsJobDao crowdTagsJobDao;
    
    @Resource
    private IRedisService redisService;
    
    /**
     * 根据标签ID和批次ID查询人群标签任务实体信息
     *
     * @param tagId   标签ID，用于标识特定的人群标签
     * @param batchId 批次ID，用于标识特定的任务批次
     * @return CrowdTagsJobEntity 人群标签任务实体，包含标签类型、标签规则、统计开始时间和结束时间等信息
     */
    @Override
    public CrowdTagsJobEntity queryCrowTagsJobEntity(String tagId, String batchId) {
        
        // 构造查询条件对象
        CrowdTagsJob crowdTagsJobReq = new CrowdTagsJob();
        crowdTagsJobReq.setTagId(tagId);
        crowdTagsJobReq.setBatchId(batchId);
        
        // 调用DAO层查询人群标签任务信息
        CrowdTagsJob crowdTagsJobRes = crowdTagsJobDao.queryCrowdTagsJob(crowdTagsJobReq);
        
        // 将查询结果转换为实体对象并返回
        return CrowdTagsJobEntity.builder()
            .tagType(crowdTagsJobRes.getTagType())
            .tagRule(crowdTagsJobRes.getTagRule())
            .statStartTime(crowdTagsJobRes.getStatStartTime())
            .statEndTime(crowdTagsJobRes.getStatEndTime())
            .build();
    }
    
    /**
     * 向指定的人群标签中添加用户ID
     *
     * @param tagId  标签ID，用于标识特定的人群标签
     * @param userId 用户ID，需要添加到标签中的用户标识
     */
    @Override
    public void addCrowdTagsUserId(String tagId, String userId) {
        // 构造人群标签详情对象
        CrowdTagsDetail crowdTagsDetailReq = new CrowdTagsDetail();
        crowdTagsDetailReq.setTagId(tagId);
        crowdTagsDetailReq.setUserId(userId);
        
        try {
            // 将用户ID添加到数据库的人群标签详情表中
            crowdTagsDetailDao.addCrowdTagsUserId(crowdTagsDetailReq);
            
            // 获取Redis中对应标签的位图，并设置用户ID对应的位位置
            RBitSet bitSet = redisService.getBitSet(tagId);
            bitSet.set(redisService.getIndexFromUserId(userId));
        } catch (DuplicateKeyException ignore) {
            // 捕获重复键异常，当用户已存在于标签中时忽略该异常
        }
    }
    
    /**
     * 更新人群标签的统计数据
     *
     * @param tagId 标签ID，用于标识需要更新统计信息的特定人群标签
     * @param size  统计数量，表示该标签下用户的总数
     */
    @Override
    public void updateCrowdTagsStatistics(String tagId, int size) {
        // 构造人群标签对象并设置统计信息
        CrowdTags crowdTagsReq = new CrowdTags();
        crowdTagsReq.setTagId(tagId);
        crowdTagsReq.setStatistics(size);
        
        // 调用DAO层更新人群标签的统计数据
        crowdTagsDao.updateCrowdTagsStatistics(crowdTagsReq);
    }
    
}
