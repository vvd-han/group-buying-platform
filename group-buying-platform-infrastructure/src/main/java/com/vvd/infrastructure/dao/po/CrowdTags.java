package com.vvd.infrastructure.dao.po;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vvd-han
 * @description 人群标签
 * @create 2026-02-09 21:55
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrowdTags {
    
    /** 自增ID */
    private Long id;
    /** 人群ID */
    private String tagId;
    /** 人群名称 */
    private String tagName;
    /** 人群描述 */
    private String tagDesc;
    /** 人群标签统计量 */
    private Integer statistics;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    
}
