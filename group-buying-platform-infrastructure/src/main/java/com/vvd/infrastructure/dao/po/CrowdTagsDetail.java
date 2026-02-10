package com.vvd.infrastructure.dao.po;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vvd-han
 * @description 人群标签明细
 * @create 2026-02-09 21:57
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrowdTagsDetail {
    
    /** 自增ID */
    private Long id;
    /** 人群ID */
    private String tagId;
    /** 用户ID */
    private String userId;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    
}
