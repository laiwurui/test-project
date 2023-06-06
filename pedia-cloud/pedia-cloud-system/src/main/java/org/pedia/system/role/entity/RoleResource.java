package org.pedia.system.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@TableName("t_role_resource")
public class RoleResource implements Serializable {

    private static final long serialVersionUID = 568523154L;

    private String id;

    private String roleId;

    private String resourceId;
}
