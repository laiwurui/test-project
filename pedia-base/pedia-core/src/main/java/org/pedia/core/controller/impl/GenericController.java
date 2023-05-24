package org.pedia.core.controller.impl;

import org.pedia.core.dto.Condition;
import org.pedia.core.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class GenericController<T> extends AbstractCommonController<T>{

    @Override
    public Result<List<T>> advancedQuery(List<Condition> list) {
        return Result.success(commonService.advancedQuery(list));
    }

    @Override
    @GetMapping("/submit")
    public Result<?> submit() {
        return Result.success();
    }

    @Override
    @GetMapping("/audit")
    public Result<?> audit() {
        return Result.success();
    }

    @Override
    @GetMapping("/view")
    public Result<T> view(String primaryKey) {
        return Result.success(commonService.view(primaryKey));
    }

    @Override
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody T entity) {
        return Result.success(commonService.save(entity));
    }

    @Override
    @GetMapping("/delete")
    public Result<Boolean> remove(String primaryKey) {
        return Result.success(commonService.remove(primaryKey));
    }
}
