package com.fqf.warehouse.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 入库单
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InStore {
    private Integer insId;

    private Integer storeId;

    private Integer productId;

    private Integer inNum;

    private Integer createBy;

    private Date createTime;

    /**
    * 0 否 1 是
    */
    private String isIn;

    private String productName;//商品名称

    private String startTime;//起始时间

    private String endTime;//结束时间

    private String storeName;//仓库名称

    private String userCode;//创建入库单的用户的名称

    private BigDecimal inPrice;//商品入库价格
}