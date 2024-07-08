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
 * @Description 课程学习实体
 * @Version 2.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("rems_course_learning")
public class CourseLearningEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID(课程学习编号)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 课程学习标题
     */
    private String courseTitle;
    /**
     * 课程学习图片
     */
    private String courseImg;
    /**
     * 课程学习简述
     */
    private String courseSm;
    /**
     * 课程学习内容
     */
    private String courseContent;
    /**
     * 课程学习索引
     */
    private String courseIndex;
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
