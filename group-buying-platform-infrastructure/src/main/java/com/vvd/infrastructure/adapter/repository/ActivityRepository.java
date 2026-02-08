package com.vvd.infrastructure.adapter.repository;

import com.vvd.domain.activity.adapter.repository.IActivityRepository;
import com.vvd.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.vvd.domain.activity.model.valobj.SkuVO;
import com.vvd.infrastructure.dao.IGroupBuyActivityDao;
import com.vvd.infrastructure.dao.IGroupBuyDiscountDao;
import com.vvd.infrastructure.dao.ISkuDao;
import com.vvd.infrastructure.dao.po.GroupBuyActivity;
import com.vvd.infrastructure.dao.po.GroupBuyDiscount;
import com.vvd.infrastructure.dao.po.Sku;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 * @author vvd-han
 * @description
 * @create 2026-02-08 16:02
 */
@Repository
public class ActivityRepository implements IActivityRepository {
    
    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;
    
    @Resource
    private IGroupBuyDiscountDao groupBuyDiscountDao;
    
    @Resource
    private ISkuDao skuDao;
    
    @Override
    public GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(String source, String channel) {
        GroupBuyActivity groupBuyActivityReq = new GroupBuyActivity();
        groupBuyActivityReq.setSource(source);
        groupBuyActivityReq.setChannel(channel);
        GroupBuyActivity groupBuyActivityRes = groupBuyActivityDao.queryValidGroupBuyActivity(groupBuyActivityReq);
        
        String discountId = groupBuyActivityRes.getDiscountId();
        GroupBuyDiscount groupBuyDiscountRes = groupBuyDiscountDao.queryGroupBuyActivityByDiscountId(discountId);
        
        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount = GroupBuyActivityDiscountVO.GroupBuyDiscount.builder()
            .discountName(groupBuyDiscountRes.getDiscountName())
            .discountDesc(groupBuyDiscountRes.getDiscountDesc())
            .discountType(groupBuyDiscountRes.getDiscountType())
            .marketPlan(groupBuyDiscountRes.getMarketPlan())
            .marketExpr(groupBuyDiscountRes.getMarketExpr())
            .tagId(groupBuyDiscountRes.getTagId())
            .build();
        
        return GroupBuyActivityDiscountVO.builder()
            .activityId(groupBuyActivityRes.getActivityId())
            .activityName(groupBuyActivityRes.getActivityName())
            .source(groupBuyActivityRes.getSource())
            .channel(groupBuyActivityRes.getChannel())
            .goodsId(groupBuyActivityRes.getGoodsId())
            .groupBuyDiscount(groupBuyDiscount)
            .groupType(groupBuyActivityRes.getGroupType())
            .takeLimitCount(groupBuyActivityRes.getTakeLimitCount())
            .target(groupBuyActivityRes.getTarget())
            .validTime(groupBuyActivityRes.getValidTime())
            .status(groupBuyActivityRes.getStatus())
            .startTime(groupBuyActivityRes.getStartTime())
            .endTime(groupBuyActivityRes.getEndTime())
            .tagId(groupBuyActivityRes.getTagId())
            .tagScope(groupBuyActivityRes.getTagScope())
            .build();
    }
    
    @Override
    public SkuVO querySkuByGoodsId(String goodsId) {
        Sku sku = skuDao.querySkuByGoodsId(goodsId);
        return SkuVO.builder()
            .goodsId(sku.getGoodsId())
            .goodsName(sku.getGoodsName())
            .originalPrice(sku.getOriginalPrice())
            .build();
    }
    
}
