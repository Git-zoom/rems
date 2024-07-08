package com.rems.boot.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description 用户类型枚举类
 * @Version 1.0
 */
@Getter
public enum UserTypeEnum {

    ADMIN("admin", "管理员"),

    USER("user", "普通用户"),

    STUDENT("student", "学生"),

    TEACHER("teacher", "教师");

    @EnumValue
    private final String code;
    @JsonValue
    private final String name;

    UserTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (UserTypeEnum userTypeEnum : UserTypeEnum.values()) {
            if (userTypeEnum.getCode().equals(code)) {
                return userTypeEnum.getName();
            }
        }
        throw new IllegalArgumentException("不存在的用户类型");
    }

}
