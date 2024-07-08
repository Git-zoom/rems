package com.rems.boot.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rems.boot.entity.PopularNavEntity;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 热门导航 Mapper
 * @Version 1.0
 */
public interface PopularNavMapper extends BaseMapper<PopularNavEntity> {

    boolean deleteBatch(List<Long> ids);

}
