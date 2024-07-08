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
import com.rems.boot.entity.QuestionEntity;
import com.rems.boot.mapper.QuestionMapper;
import com.rems.boot.service.QuestionService;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 问题查询Service实现类
 * @Version 1.0
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, QuestionEntity> implements QuestionService {

    @Override
    @Transactional
    public boolean deleteBatch(List<Long> ids) {
        return removeByIds(ids);
    }

    @Override
    public QuestionEntity add(@NotNull QuestionEntity entity) {
        save(entity);
        return entity;
    }

    @Override
    public void delete(QuestionEntity entity) {
        removeById(entity.getId());
    }

    @Override
    public void update(QuestionEntity entity) {
        LambdaUpdateWrapper<QuestionEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(QuestionEntity::getId, entity.getId());

        update(entity, updateWrapper);
    }

    @Override
    public QuestionEntity get(@NotNull QuestionEntity entity) {
        LambdaQueryWrapper<QuestionEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(QuestionEntity::getId, entity.getId());
        return getOne(queryWrapper);
    }

    @Override
    public List<QuestionEntity> list(QuestionEntity query) {
        return list(getQueryWrapper(query));
    }

    @Override
    public Page<QuestionEntity> page(QuestionEntity query, Page<QuestionEntity> page) {
        return page(page, getQueryWrapper(query));
    }

    private LambdaQueryWrapper<QuestionEntity> getQueryWrapper(QuestionEntity query) {
        LambdaQueryWrapper<QuestionEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(query.getQuesTitle())) {
            queryWrapper.like(QuestionEntity::getQuesTitle, query.getQuesTitle());
        }
        return queryWrapper;
    }

}
