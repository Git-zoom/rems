package com.rems.boot.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 热门导航实体
 * @Version 2.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("rems_popular_nav")
public class PopularNavEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 导航排名
     */
    private String navNum;
    /**
     * 导航名称
     */
    private String navName;
    /**
     * 导航热度
     */
    private int navHeat;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
