package com.rems.boot.service;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 基础的实体服务接口
 * @Version 1.0
 */
public interface EntityService<T> {

    T add(T entity);

    void delete(T entity);

    void update(T entity);

    T get(T entity);

}
