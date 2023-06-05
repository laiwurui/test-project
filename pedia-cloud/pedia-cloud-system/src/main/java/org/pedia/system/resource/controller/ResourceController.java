package org.pedia.system.resource.controller;

import org.pedia.core.controller.impl.GenericController;
import org.pedia.system.resource.entity.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController extends GenericController<Resource> {
}
