package com.rems.boot.service;

import java.util.List;

import com.rems.boot.entity.UserEntity;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 用户 Service
 * @Version 1.0
 */
public interface UserService extends EntityQueryService<UserEntity, UserEntity> {

    boolean deleteBatch(List<Long> ids);

    // 检验用户登录
    UserEntity checkLogin(String username, String password);

}
