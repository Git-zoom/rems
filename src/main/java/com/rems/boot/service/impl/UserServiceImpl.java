package com.rems.boot.service.impl;

import java.util.List;
import java.util.Objects;

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
        UserEntity oldUserEntity = UserEntity.builder().username(username).password(password).build();
        UserEntity userEntity = get(oldUserEntity);
        if (userEntity != null && userEntity.getPassword().equals(password)) {
            return userEntity;
        }
        throw new RuntimeException("用户名或密码错误");
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
    public List<UserEntity> list(UserEntity query) {
        return list(getQueryWrapper(query));
    }

    @Override
    public Page<UserEntity> page(UserEntity query, Page<UserEntity> page) {
        return page(page, getQueryWrapper(query));
    }

    private LambdaQueryWrapper<UserEntity> getQueryWrapper(UserEntity query) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(query.getUsername())) {
            queryWrapper.like(UserEntity::getUsername, query.getUsername());
        }
        return queryWrapper;
    }

}
