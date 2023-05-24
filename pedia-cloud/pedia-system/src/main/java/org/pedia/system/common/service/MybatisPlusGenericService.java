package org.pedia.system.common.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pedia.core.dto.Condition;
import org.pedia.core.service.ICommonService;

import java.lang.reflect.AnnotatedType;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 默认使用 mybatis-plus 的Service
 * @param <M> mapper
 * @param <T> entity
 */
public class MybatisPlusGenericService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IService<T>, ICommonService<T> {


    @Override
    public Map<String, String> getFields() {
        Class clazz = this.getClass();
        AnnotatedType[] annotatedInterfaces = clazz.getAnnotatedInterfaces();
        return null;
    }

    @Override
    public List<T> advancedQuery(List<Condition> conditions) {
        return Collections.emptyList();
    }

    @Override
    public T view(String primaryKey) {
        return this.getById(primaryKey);
    }

    @Override
    public boolean remove(String primaryKey) {
        return this.removeById(primaryKey);
    }

    @Override
    public void submit() {

    }

    @Override
    public void audit() {

    }

    @Override
    public boolean save(T entity) {
        return super.save(entity);
    }
}
