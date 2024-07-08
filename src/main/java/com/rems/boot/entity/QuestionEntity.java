package com.rems.boot.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 查询问题模块的实体类
 * @Version 2.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("rems_question")
public class QuestionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 问题ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 问题标题
     */
    private String quesTitle;
    /**
     * 问题作者
     */
    private String quesAuthor;
    /**
     * 问题发表日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date quesDate;
    /**
     * 问题来源
     */
    private String quesSource;
    /**
     * 问题内容
     */
    private String quesTxt;
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
