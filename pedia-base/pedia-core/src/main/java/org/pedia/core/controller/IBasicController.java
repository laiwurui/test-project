package org.pedia.core.controller;

import org.pedia.core.vo.Result;

public interface IBasicController<T> {

    /**
     * view entity
     * @param primaryKey 主键
     * @return T
     */
    Result<T> view(String primaryKey);

    /**
     * save entity
     * @param entity 实体
     */
    Result<Boolean> save(T entity);

    /**
     * remove
     * @param primaryKey 主键
     */
    Result<Boolean> remove(String primaryKey);
}
