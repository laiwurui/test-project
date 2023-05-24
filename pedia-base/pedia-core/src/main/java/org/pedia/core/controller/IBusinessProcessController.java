package org.pedia.core.controller;

import org.pedia.core.vo.Result;

public interface IBusinessProcessController<T> {

    /**
     * 提交
     */
    Result<?> submit();

    /**
     * 审核
     */
    Result<?> audit();
}
