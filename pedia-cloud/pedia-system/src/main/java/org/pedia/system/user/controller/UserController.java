package org.pedia.system.user.controller;

import lombok.AllArgsConstructor;
import org.pedia.core.controller.impl.GenericController;
import org.pedia.system.user.entity.User;
import org.pedia.system.user.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController extends GenericController<User> {

    private final IUserService userService;

}
