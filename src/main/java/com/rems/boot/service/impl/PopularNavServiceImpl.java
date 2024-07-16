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
import com.rems.boot.entity.PopularNavEntity;
import com.rems.boot.mapper.PopularNavMapper;
import com.rems.boot.service.PopularNavService;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 热门导航Service实现类
 * @Version 1.0
 */
@Service
public class PopularNavServiceImpl extends ServiceImpl<PopularNavMapper, PopularNavEntity> implements PopularNavService {

    @Override
    @Transactional
    public boolean deleteBatch(List<Long> ids) {
        return removeByIds(ids);
    }

    @Override
    public boolean clickUpdate(Integer id) {
        if (Objects.isNull(id)) {
            throw new NullPointerException("#clickUpdate 导航ID不能为空!");
        }
        PopularNavEntity popularNavEntity = PopularNavEntity.builder().id(id).build();
        PopularNavEntity oldNavEntity = get(popularNavEntity);
        if (Objects.isNull(oldNavEntity)) {
            throw new NullPointerException("#clickUpdate 导航不存在!");
        }
        oldNavEntity.setNavHeat(oldNavEntity.getNavHeat() + 1);
        update(oldNavEntity);
        return true;
    }

    @Override
    public PopularNavEntity add(@NotNull PopularNavEntity entity) {
        save(entity);
        return entity;
    }

    @Override
    public void delete(PopularNavEntity entity) {
        removeById(entity.getId());
    }

    @Override
    public void update(PopularNavEntity entity) {
        LambdaUpdateWrapper<PopularNavEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(PopularNavEntity::getId, entity.getId());

        update(entity, updateWrapper);
    }

    @Override
    public PopularNavEntity get(@NotNull PopularNavEntity entity) {
        LambdaQueryWrapper<PopularNavEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PopularNavEntity::getId, entity.getId());
        return getOne(queryWrapper);
    }

    @Override
    public List<PopularNavEntity> list(PopularNavEntity query) {
        return list(getQueryWrapper(query));
    }

    @Override
    public Page<PopularNavEntity> page(PopularNavEntity query, Page<PopularNavEntity> page) {

        return page(page, getQueryWrapper(query));
    }

    private LambdaQueryWrapper<PopularNavEntity> getQueryWrapper(PopularNavEntity query) {
        LambdaQueryWrapper<PopularNavEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(query.getNavName())) {
            queryWrapper.like(PopularNavEntity::getNavName, query.getNavName());
        }
        queryWrapper.orderByDesc(PopularNavEntity::getNavHeat);
        return queryWrapper;
    }

}
