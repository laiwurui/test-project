package org.pedia.core.controller;

import org.pedia.core.dto.Condition;
import org.pedia.core.vo.Result;

import java.util.List;
import java.util.Map;

public interface IAdvancedController<T> {

    Result<Map<String, String>> getFields();

    Result<List<T>> advancedQuery(List<Condition> conditions);
}
