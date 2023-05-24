package org.pedia.core.service;

import org.pedia.core.dto.Condition;

import java.util.List;
import java.util.Map;

public interface IAdvanceService<T> {

    Map<String, String> getFields();

    List<T> advancedQuery(List<Condition> conditions);
}
