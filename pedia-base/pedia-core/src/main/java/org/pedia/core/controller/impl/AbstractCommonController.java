package org.pedia.core.controller.impl;

import org.pedia.core.controller.IAdvancedController;
import org.pedia.core.controller.IBasicController;
import org.pedia.core.controller.IBusinessProcessController;
import org.pedia.core.service.ICommonService;
import org.pedia.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public abstract class AbstractCommonController<T> implements IAdvancedController<T>, IBasicController<T>, IBusinessProcessController<T> {

    protected ICommonService<T> commonService;

    @Autowired
    public void setCommonService(ICommonService<T> commonService) {
        this.commonService = commonService;
    }

    @Override
    public Result<Map<String, String>> getFields() {
        return Result.success(commonService.getFields());
    }
}
