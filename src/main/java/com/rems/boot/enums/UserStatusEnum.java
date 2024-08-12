package com.rems.boot.enums;

/**
 * @Author qinj
 * @Date 2024/8/9
 * @Description TODO
 * @Version 1.0
 */
public enum UserStatusEnum {
    ACTIVE("激活"),
    INACTIVE("未激活"),
    PENDING("待审核");


    private String desc;


    UserStatusEnum(String desc) {
        this.desc = desc;
    }

}
