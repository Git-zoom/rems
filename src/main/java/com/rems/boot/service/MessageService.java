package com.rems.boot.service;

import java.util.List;

import com.rems.boot.entity.MessageEntity;
/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 留言Service
 * @Version 1.0
 */
public interface MessageService extends EntityQueryService<MessageEntity, MessageEntity> {

    boolean deleteBatch(List<Long> ids);

}
