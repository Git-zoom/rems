package com.rems.boot.model.req;

import lombok.Data;

/**
 * @Author qinj
 * @Date 2024/8/28
 * @Description 修改密码的请求参数
 * @Version 1.0
 */
@Data
public class ModifyPasswordReq {

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 旧密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String password;

}
