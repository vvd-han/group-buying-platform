package com.vvd.domain.activity.service.trial.thread;

import com.vvd.domain.activity.adapter.repository.IActivityRepository;
import com.vvd.domain.activity.model.valobj.SkuVO;
import java.util.concurrent.Callable;

/**
 * @author vvd-han
 * @description
 * @create 2026-02-08 19:45
 */
public class QuerySkuVOFromDBThreadTask implements Callable<SkuVO> {
    
    private final String goodsId;
    
    private final IActivityRepository activityRepository;
    
    public QuerySkuVOFromDBThreadTask(String goodsId, IActivityRepository activityRepository) {
        this.goodsId = goodsId;
        this.activityRepository = activityRepository;
    }
    
    @Override
    public SkuVO call() throws Exception {
        return activityRepository.querySkuByGoodsId(goodsId);
    }
    
}
