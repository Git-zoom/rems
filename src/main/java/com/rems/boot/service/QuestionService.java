package com.rems.boot.service;

import java.util.List;

import com.rems.boot.entity.QuestionEntity;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 问题查询Service
 * @Version 1.0
 */
public interface QuestionService extends EntityQueryService<QuestionEntity, QuestionEntity> {

    boolean deleteBatch(List<Long> ids);

}
