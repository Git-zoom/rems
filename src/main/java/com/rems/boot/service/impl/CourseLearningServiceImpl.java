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
import com.rems.boot.entity.CourseLearningEntity;
import com.rems.boot.mapper.CourseLearningMapper;
import com.rems.boot.service.CourseLearningService;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 课程学习Service实现类
 * @Version 1.0
 */
@Service
public class CourseLearningServiceImpl extends ServiceImpl<CourseLearningMapper, CourseLearningEntity> implements CourseLearningService {

    @Override
    @Transactional
    public boolean deleteBatch(List<Long> ids) {
        return removeByIds(ids);
    }

    @Override
    public CourseLearningEntity add(@NotNull CourseLearningEntity entity) {
        save(entity);
        return entity;
    }

    @Override
    public void delete(CourseLearningEntity entity) {
        removeById(entity.getId());
    }

    @Override
    public void update(CourseLearningEntity entity) {
        LambdaUpdateWrapper<CourseLearningEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(CourseLearningEntity::getId, entity.getId());

        update(entity, updateWrapper);
    }

    @Override
    public CourseLearningEntity get(@NotNull CourseLearningEntity entity) {
        LambdaQueryWrapper<CourseLearningEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseLearningEntity::getId, entity.getId());
        return getOne(queryWrapper);
    }

    @Override
    public List<CourseLearningEntity> list(CourseLearningEntity query) {
        return list(getQueryWrapper(query));
    }

    @Override
    public Page<CourseLearningEntity> page(CourseLearningEntity query, Page<CourseLearningEntity> page) {

        return page(page, getQueryWrapper(query));
    }

    private LambdaQueryWrapper<CourseLearningEntity> getQueryWrapper(CourseLearningEntity query) {
        LambdaQueryWrapper<CourseLearningEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(query.getCourseTitle())) {
            queryWrapper.like(CourseLearningEntity::getCourseTitle, query.getCourseTitle());
        }
        return queryWrapper;
    }

}
