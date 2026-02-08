package com.vvd.domain.activity.service.trial.thread;

import com.vvd.domain.activity.adapter.repository.IActivityRepository;
import com.vvd.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import java.util.concurrent.Callable;

/**
 * @author vvd
 * @description
 * @create 2026-02-06 20:51
 */
public class QueryGroupBuyActivityDiscountVOThreadTask implements Callable<GroupBuyActivityDiscountVO> {
    
    private final String source;
    private final String channel;
    private final IActivityRepository activityRepository;
    
    public QueryGroupBuyActivityDiscountVOThreadTask(String source, String channel,
        IActivityRepository activityRepository) {
        this.source = source;
        this.channel = channel;
        this.activityRepository = activityRepository;
    }
    
    @Override
    public GroupBuyActivityDiscountVO call() throws Exception {
        return activityRepository.queryGroupBuyActivityDiscountVO(source, channel);
    }
    
}
