package org.pedia.system.resource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import org.pedia.core.entity.BaseEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("t_resource")
public class Resource extends BaseEntity {

    /**
     * 资源名称
     * resource name
     */
    private String name;
    /**
     * 路径
     * resource path
     */
    private String path;
    /**
     * vue组件
     * vue component for page render
     */
    private String component;
    /**
     * 资源权限标识
     * resource authority
     */
    private String authority;
    /**
     * 上级id
     * parentId
     */
    private String parentId;
    /**
     * 图标
     * icon
     */
    private String icon;
    /**
     * 资源类型 0 菜单， 1 按钮
     * resource type 0 menu , 1 button
     */
    private Integer type;
    /**
     * 排序号
     * order number
     */
    private Integer orderNum;
    /**
     * 是否是叶子节点
     * is leaf or not
     */
    private Boolean leaf;
}
