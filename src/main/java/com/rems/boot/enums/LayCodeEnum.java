package com.rems.boot.enums;

import lombok.Getter;

/**
 * @Author qinj
 * @Date 2024/7/9
 * @Description lay code 枚举
 * @Version 1.0
 */
@Getter
public enum LayCodeEnum {

    // 成功状态码,
    SUCCESS(0),
    // 错误状态码
    ERROR(1);

    private final int code;

    LayCodeEnum(int code) {
        this.code = code;
    }

}
