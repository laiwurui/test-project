-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource`  (
                               `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
                               `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
                               `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径',
                               `component` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'vue前端组件',
                               `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
                               `type` int(0) NULL DEFAULT NULL COMMENT '菜单类型(0:菜单menu,1:按钮button)',
                               `order_num` int(0) NULL DEFAULT NULL COMMENT '排序',
                               `authority` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
                               `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级id',
                               `leaf` tinyint(1) NULL DEFAULT NULL COMMENT '是否为叶子节点(0:no,1:yes)',
                               `permission` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据权限',
                               `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                               `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                               `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                               `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                               `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除标识(1:DELETE，0:NORMAL)',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES ('1', '首页', '/home', NULL, NULL, 0, 0, NULL, NULL, 1, NULL, NULL, '2023-06-12 08:56:39', NULL, NULL, 0);
INSERT INTO `t_resource` VALUES ('2', '用户管理', '/user/manage', '/views/user/manage/index', NULL, 0, 1, NULL, NULL, 0, NULL, NULL, '2023-06-12 08:59:57', NULL, NULL, 0);
INSERT INTO `t_resource` VALUES ('3', '用户新增', NULL, NULL, NULL, 1, 1001, 'user:add', NULL, 1, NULL, NULL, '2023-06-12 09:04:23', NULL, NULL, 0);
INSERT INTO `t_resource` VALUES ('4', '用户编辑', NULL, NULL, NULL, 1, 1002, 'user:edit', NULL, 1, NULL, NULL, '2023-06-12 09:05:20', NULL, NULL, 0);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
                           `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
                           `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
                           `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                           `permission` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据权限',
                           `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                           `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                           `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                           `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                           `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除标识(1:DELETE，0:NORMAL)',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', 'admin', NULL, NULL, '2023-06-12 08:54:55', NULL, NULL, 0);

-- ----------------------------
-- Table structure for t_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resource`;
CREATE TABLE `t_role_resource`  (
                                    `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
                                    `resource_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源id',
                                    `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    INDEX `INDEX_ROLE_ID`(`role_id`) USING BTREE,
                                    INDEX `INDEX_RESOURCE_ID`(`resource_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_resource
-- ----------------------------
INSERT INTO `t_role_resource` VALUES ('1', '1', '1');
INSERT INTO `t_role_resource` VALUES ('2', '2', '1');
INSERT INTO `t_role_resource` VALUES ('3', '3', '1');
INSERT INTO `t_role_resource` VALUES ('4', '4', '1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
                           `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
                           `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
                           `phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
                           `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                           `salt` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
                           `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
                           `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
                           `enabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否启用(1:YES，0:NO)',
                           `dept_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门id',
                           `company_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司id',
                           `permission` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据权限',
                           `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人id',
                           `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                           `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人id',
                           `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                           `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除标识(1:DELETE，0:NORMAL)',
                           PRIMARY KEY (`id`) USING BTREE,
                           INDEX `INDEX_USER_USERNAME`(`username`) USING BTREE COMMENT '用户名索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '管理员', '13558791441', '850874993@qq.com', 'SALT==', '', '$2a$10$CzUl96o7ghOlueLXu8yNF.lJ3bu13PQ6JEZN8VWRCbWguKGjeWkFG', 1, NULL, NULL, NULL, '0', '2023-05-17 11:59:11', '0', '2023-05-17 11:59:13', 0);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
                                `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
                                `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
                                `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
                                PRIMARY KEY (`id`) USING BTREE,
                                INDEX `INDEX_ROLE_ID`(`role_id`) USING BTREE,
                                INDEX `INDEX_USER_ID`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1');

SET FOREIGN_KEY_CHECKS = 1;
