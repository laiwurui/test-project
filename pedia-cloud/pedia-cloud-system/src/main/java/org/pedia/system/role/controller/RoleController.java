package org.pedia.system.role.controller;

import org.pedia.core.controller.impl.GenericController;
import org.pedia.system.role.entity.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController extends GenericController<Role> {
}
