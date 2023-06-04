package org.pedia.core.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class DataStructureUtil {

    /**
     * 按parent属性构建树结构
     * @param list origin list 源列表
     * @param childrenFiled T中表示子节点的字段
     * @param parentField T中表示父节点的字段
     * @param parentRelateField parentField值的来源字段
     * @param rootPredicate 断言 是否为根节点
     * @param leafPredicate 断言 是否为叶子节点
     * @param <T> 类型 the Class that is supporting tree
     * @return 返回值 List<T> 经处理后的列表
     */
    public static <T> List<T> listToTree(List<T> list, String childrenFiled,
                                         String parentField, String parentRelateField,
                                         @NonNull Predicate<T> rootPredicate, @Nullable Predicate<T> leafPredicate) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        ArrayList<T> arrayList = new ArrayList<>();
        HashMap<Object, List<T>> childrenRef = new HashMap<>();
        try {
            for (T t : list) {
                Class<?> tClass = t.getClass();
                Field parentRelate = tClass.getDeclaredField(parentRelateField);
                parentRelate.setAccessible(true);
                Object parentRelateValue = ReflectionUtils.getField(parentRelate, t);
                Field children = tClass.getDeclaredField(childrenFiled);
                children.setAccessible(true);
                List<T> childrenValue = (List<T>) ReflectionUtils.getField(children, t);
                boolean isRoot = rootPredicate.test(t);
                boolean isLeaf = false;
                if(leafPredicate != null) {
                    isLeaf = leafPredicate.test(t);
                }
                // 处理每个节点的children属性，并将children缓存到map
                if (childrenValue == null && !isLeaf) {
                    if (childrenRef.containsKey(parentRelateValue)) {
                        childrenValue = childrenRef.get(parentRelateValue);
                    } else {
                        childrenValue = new ArrayList<T>(4);
                        childrenRef.put(parentRelateValue, childrenValue);
                    }
                    ReflectionUtils.setField(children, t, childrenValue);
                }
                if (isRoot) {
                    // 是根节点
                    arrayList.add(t);
                } else {
                    Field parent = tClass.getDeclaredField(parentField);
                    parent.setAccessible(true);
                    Object parentValue = ReflectionUtils.getField(parent, t);
                    // 是子节点
                    if (childrenRef.containsKey(parentValue)) {
                        List<T> ch = childrenRef.get(parentValue);
                        ch.add(t);
                    } else {
                        List<T> ch = new ArrayList<>(4);
                        ch.add(t);
                        childrenRef.put(parentValue, ch);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("List构建Tree结构失败");
        }
        return arrayList;
    }
}
