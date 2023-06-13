package org.pedia.system.user.dto;

import lombok.*;
import lombok.experimental.Accessors;
import org.pedia.core.dto.PageDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends PageDTO {

    /**
     * 用户名称
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 盐
     */
    private String salt;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否启用（1：启用，0：不启用）
     */
    private Integer enabled;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 角色id
     */
    private List<String> roleIds;
}
