package org.pedia.core.service;

public interface IBasicService<T> {

    /**
     * view entity
     * @param primaryKey 主键
     * @return T
     */
    T view(String primaryKey);

    /**
     * save entity
     * @param entity 实体
     */
    boolean save(T entity);

    /**
     * remove
     * @param primaryKey 主键
     */
    boolean remove(String primaryKey);



}
