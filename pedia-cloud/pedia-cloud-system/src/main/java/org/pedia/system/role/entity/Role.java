package org.pedia.system.role.entity;

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
@TableName("t_role")
public class Role extends BaseEntity {

    /**
     * 角色名称
     * role name
     */
    private String name;
    /**
     * 备注
     * remark
     */
    private String remark;

}
