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
import com.rems.boot.entity.MessageEntity;
import com.rems.boot.mapper.MessageMapper;
import com.rems.boot.service.MessageService;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 留言服务实现类
 * @Version 1.0
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageEntity> implements MessageService {

    @Override
    @Transactional
    public boolean deleteBatch(List<Long> ids) {
        return removeByIds(ids);
    }

    @Override
    public MessageEntity add(@NotNull MessageEntity entity) {
        save(entity);
        return entity;
    }

    @Override
    public void delete(MessageEntity entity) {
        removeById(entity.getId());
    }

    @Override
    public void update(MessageEntity entity) {
        LambdaUpdateWrapper<MessageEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(MessageEntity::getId, entity.getId());

        update(entity, updateWrapper);
    }

    @Override
    public MessageEntity get(@NotNull MessageEntity entity) {
        LambdaQueryWrapper<MessageEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MessageEntity::getId, entity.getId());
        return getOne(queryWrapper);
    }

    @Override
    public List<MessageEntity> list(MessageEntity query) {
        return list(getQueryWrapper(query));
    }

    @Override
    public Page<MessageEntity> page(MessageEntity query, Page<MessageEntity> page) {
        return page(page, getQueryWrapper(query));
    }

    private LambdaQueryWrapper<MessageEntity> getQueryWrapper(MessageEntity query) {
        LambdaQueryWrapper<MessageEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(query.getMsgUsername())) {
            queryWrapper.like(MessageEntity::getMsgUsername, query.getMsgUsername());
        }
        return queryWrapper;
    }

}
