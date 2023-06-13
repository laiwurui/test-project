package org.pedia.demo.controller;

import org.pedia.core.vo.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo1")
    @PreAuthorize("hasAuthority('SCOPE_message:read')")
    public Result<Object> demo1() {
        return Result.success("demo1");
    }

    @GetMapping("/demo2")
    @PreAuthorize("hasAuthority('user:add')")
    public Result<Object> demo2() {
        return Result.success("demo2");
    }
}
