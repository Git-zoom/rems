package com.rems.boot.service.impl;

import java.util.List;
import java.util.Objects;

import com.rems.boot.enums.UserTypeEnum;
import com.rems.boot.model.req.ModifyPasswordReq;
import com.rems.boot.model.req.UserQuery;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rems.boot.entity.UserEntity;
import com.rems.boot.mapper.UserMapper;
import com.rems.boot.service.UserService;
import com.rems.boot.utils.MD5Util;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 用户Service实现类
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    @Transactional
    public boolean deleteBatch(List<Long> ids) {
        return removeByIds(ids);
    }

    @Override
    public UserEntity checkLogin(String username, String password) {
        UserEntity oldUserEntity = UserEntity.builder().username(username).build();
        UserEntity userEntity = get(oldUserEntity);
        String saltPassword = userEntity.getPassword();
        if (!MD5Util.verifySaltPassword(password, saltPassword)) {
            throw new RuntimeException("用户名或密码错误");
        }
        return userEntity;
    }

    @Override
    public boolean modifyPassword(ModifyPasswordReq req) {
        Objects.requireNonNull(req, "#modifyPassword ModifyPasswordReq cannot be null");
        Integer userId = req.getUserId();
        UserEntity userEntity = get(UserEntity.builder().id(userId).build());
        if (Objects.nonNull(userEntity)) {
            String oldPassword = req.getOldPassword();
            String newPassword = req.getPassword();
            String saltPassword = userEntity.getPassword();

            // oldPassword 与 saltPassword 进行比对
            if (MD5Util.verifySaltPassword(oldPassword, saltPassword)) {

                // 修改密码
                userEntity.setPassword(MD5Util.generateSaltPassword(newPassword));
                update(userEntity);
                return true;
            } else {
                throw new RuntimeException("原密码错误");
            }
        }

        return false;
    }

    @Override
    public UserEntity add(@NotNull UserEntity entity) {
        save(entity);
        return entity;
    }

    @Override
    public void delete(UserEntity entity) {
        removeById(entity.getId());
    }

    @Override
    public void update(UserEntity entity) {
        LambdaUpdateWrapper<UserEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(UserEntity::getId, entity.getId());

        update(entity, updateWrapper);
    }

    @Override
    public UserEntity get(@NotNull UserEntity entity) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(entity.getId())) {
            queryWrapper.eq(UserEntity::getId, entity.getId());
        }
        if (Objects.nonNull(entity.getUsername())) {
            queryWrapper.eq(UserEntity::getUsername, entity.getUsername());
        }
        if (Objects.nonNull(entity.getPassword())) {
            queryWrapper.eq(UserEntity::getPassword, entity.getPassword());
        }
        return getOne(queryWrapper);
    }

    @Override
    public List<UserEntity> list(UserQuery query) {
        return list(getQueryWrapper(query));
    }

    @Override
    public Page<UserEntity> page(UserQuery query, Page<UserEntity> page) {
        return page(page, getQueryWrapper(query));
    }

    private LambdaQueryWrapper<UserEntity> getQueryWrapper(UserQuery query) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(query.getUsername())) {
            queryWrapper.like(UserEntity::getUsername, query.getUsername());
        }
        if (Objects.nonNull(query.getIsAdmin())) {
            if (query.getIsAdmin()){
                queryWrapper.eq(UserEntity::getRole, UserTypeEnum.ADMIN);
            } else {
                queryWrapper.ne(UserEntity::getRole, UserTypeEnum.ADMIN);
            }
        }
        return queryWrapper;
    }

}
