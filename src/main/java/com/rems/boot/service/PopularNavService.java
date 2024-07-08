package com.rems.boot.service;

import java.util.List;

import com.rems.boot.entity.PopularNavEntity;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 热门导航Service
 * @Version 1.0
 */
public interface PopularNavService extends EntityQueryService<PopularNavEntity, PopularNavEntity> {

    boolean deleteBatch(List<Long> ids);

}
