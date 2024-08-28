package com.rems.boot.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 网站留言实体
 * @Version 2.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("rems_message")
public class MessageEntity implements Serializable {

    private static final long serialVersionUID = 20240828144534L;

    /**
     * 主键ID（留言编号）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 留言用户名
     */
    private String msgUsername;
    /*
     * 留言用户头像
     */
    private String msgUserFace;
    /**
     * 留言内容
     */
    private String msgContent;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
