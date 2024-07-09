package com.rems.boot.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author qinj
 * @Date 2024/7/8
 * @Description 查询页面
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPage {

    /**
     * 页数
     */
    private Integer pageIndex = 1;
    /**
     * 每页条数
     */
    private Integer pageSize = 10;
    /**
     * 总数
     */
    private Long totalCount;

}
