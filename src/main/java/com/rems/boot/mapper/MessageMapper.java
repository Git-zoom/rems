package com.rems.boot.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rems.boot.entity.MessageEntity;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 留言Mapper
 * @Version 1.0
 */
public interface MessageMapper extends BaseMapper<MessageEntity> {


    boolean deleteBatch(List<Long> ids);
}
