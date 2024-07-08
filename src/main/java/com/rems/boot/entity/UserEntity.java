package com.rems.boot.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.rems.boot.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 用户实体类
 * @Version 2.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("rems_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID（用户编号）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 用户头像
     */
    private String face;
    /**
     * 用户类别
     */
    private UserTypeEnum type;
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
