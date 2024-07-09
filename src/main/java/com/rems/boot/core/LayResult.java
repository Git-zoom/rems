package com.rems.boot.core;

import com.rems.boot.enums.LayCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author qinj
 * @Date 2024/7/9
 * @Description Layui 表格返回结果
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LayResult<T> {

    /**
     * 状态码, 0表示成功, 非0表示失败
     */
    private Integer code;
    /**
     * 状态信息, 一般为空或错误信息
     */
    private String msg;
    /**
     * 数据总数，用于分页显示
     */
    private Long count;
    /**
     * 显示数据
     */
    private List<T> data;

    /**
     * 返回有数据信息
     */
    public static <T> LayResult<T> success(List<T> data, Long count) {
        return new LayResult<>(LayCodeEnum.SUCCESS.getCode(), "success", count, data);
    }

    /**
     * 返回无数据信息
     */
    public static <T> LayResult<T> success() {
        return new LayResult<>(LayCodeEnum.SUCCESS.getCode(), "success", 0L, null);
    }

    /**
     * 返回失败信息
     */
    public static <T> LayResult<T> error(String msg) {
        return new LayResult<>(LayCodeEnum.ERROR.getCode(), msg, 0L, null);
    }

}
