package org.pedia.system.user.controller;

import lombok.AllArgsConstructor;
import org.pedia.core.controller.impl.GenericController;
import org.pedia.core.vo.Result;
import org.pedia.system.user.entity.User;
import org.pedia.system.user.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController extends GenericController<User> {

    private final IUserService userService;

    @GetMapping("/test")
    public Result<String> testOauth() {
        return Result.success("成功");
    }
}
