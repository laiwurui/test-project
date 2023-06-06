package org.pedia.system.user.controller;

import lombok.AllArgsConstructor;
import org.pedia.core.controller.impl.GenericController;
import org.pedia.core.vo.Result;
import org.pedia.system.user.dto.UserDTO;
import org.pedia.system.user.entity.User;
import org.pedia.system.user.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController extends GenericController<User> {

    private final IUserService userService;

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('user:add')")
    public Result<String> testOauth() {
        return Result.success("成功");
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody UserDTO userDTO) {
        userService.add(userDTO);
        return Result.success();
    }

    @PostMapping("/edit")
    public Result<?> edit(@RequestBody UserDTO userDTO) {
        userService.edit(userDTO);
        return Result.success();
    }
}
