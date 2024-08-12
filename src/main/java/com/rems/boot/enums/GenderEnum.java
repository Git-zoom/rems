package com.rems.boot.enums;

/**
 * @Author qinj
 * @Date 2024/8/9
 * @Description TODO
 * @Version 1.0
 */
public enum GenderEnum {

    MALE("男"),
    FEMALE("女"),
    OTHER("其他");

    private String desc;

    GenderEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
