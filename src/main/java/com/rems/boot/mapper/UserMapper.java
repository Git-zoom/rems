package com.rems.boot.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rems.boot.entity.UserEntity;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 用户 Mapper
 * @Version 1.0
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    boolean deleteBatch(List<Long> ids);
}
