package org.pedia.system.user.entity;

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
@TableName("t_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 151316822031L;

    private String id;

    private String userId;

    private String roleId;

}
