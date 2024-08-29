package com.rems.boot.model.req;

import com.rems.boot.entity.UserEntity;
import lombok.*;

/**
 * @Author qinj
 * @Date 2024/8/29
 * @Description 用户查询请求参数
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery extends UserEntity {

    /**
     * 是否是管理员
     */
    private Boolean isAdmin = false;

}
