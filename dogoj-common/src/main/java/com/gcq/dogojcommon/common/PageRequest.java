package com.gcq.dogojcommon.common;


import com.gcq.dogojcommon.constant.CommonConstant;
import lombok.Data;

/**
 * 分页请求
 *
 * @author <a ">郭楚锜</a>
 * @from <a >QQ：2798713432</a>
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private int current = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}
