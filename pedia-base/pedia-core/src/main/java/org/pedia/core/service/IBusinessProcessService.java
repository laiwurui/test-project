package org.pedia.core.service;

public interface IBusinessProcessService<T> {

    /**
     * 提交
     */
    void submit();

    /**
     * 审核
     */
    void audit();
}
