package com.fqf.warehouse.utils;

/**
 * 常量类:
 * 定义的全部都是全局常量的接口成为常量类；属性经常会改动
 */
public interface WarehouseConstants {

    //用户未审核
    public String USER_STATE_NOT_PASS = "0";

    //用户已审核
    public String USER_STATE_PASS = "1";

    //传递token的请求头名称
    public String HEADER_TOKEN_NAME = "Token";
}
