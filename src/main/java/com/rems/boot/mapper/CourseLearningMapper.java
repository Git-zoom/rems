package com.rems.boot.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rems.boot.entity.CourseLearningEntity;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 课程学习Mapper
 * @Version 1.0
 */
public interface CourseLearningMapper extends BaseMapper<CourseLearningEntity> {

    boolean deleteBatch(List<Long> ids);

}
