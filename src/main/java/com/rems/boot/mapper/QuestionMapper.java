package com.rems.boot.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rems.boot.entity.QuestionEntity;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 问题查询 Mapper
 * @Version 1.0
 */
public interface QuestionMapper extends BaseMapper<QuestionEntity> {

    boolean deleteBatch(List<Long> ids);

}
