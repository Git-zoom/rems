package com.rems.boot.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 实体查询服务接口
 * @Version 1.0
 */
public interface EntityQueryService<T, Q extends T> extends EntityService<T> {

    List<T> list(Q query);

    Page<T> page(Q query, Page<T> page);

}
