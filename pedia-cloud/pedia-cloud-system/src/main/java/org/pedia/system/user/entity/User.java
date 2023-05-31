package org.pedia.system.user.entity;

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
@TableName("t_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 481321542L;

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
}
