package com.rems.boot.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rems.boot.enums.GenderEnum;
import com.rems.boot.enums.UserStatusEnum;
import com.rems.boot.enums.UserTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private static final long serialVersionUID = 20240828144538L;

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
     * 昵称
     */
    private String nickname;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 性别
     */
    private GenderEnum gender;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 状态
     */
    private UserStatusEnum status;
    /**
     * 角色
     */
    private UserTypeEnum role;
    /**
     * 备注
     */
    private String remark;
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
