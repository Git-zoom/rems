package com.rems.boot.service;

import java.util.List;

import com.rems.boot.entity.CourseLearningEntity;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 课程学习Service
 * @Version 1.0
 */
public interface CourseLearningService extends EntityQueryService<CourseLearningEntity, CourseLearningEntity> {

    boolean deleteBatch(List<Long> ids);

}
