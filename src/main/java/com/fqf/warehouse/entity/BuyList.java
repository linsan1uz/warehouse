package com.fqf.warehouse.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 采购单
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyList {
    private Integer buyId;

    private Integer productId;

    private Integer storeId;

    private Integer buyNum;

    private Integer factBuyNum;

    private Date buyTime;

    private Integer supplyId;

    private Integer placeId;

    private String buyUser;

    private String phone;

    /**
    * 0 否 1 是
    */
    private String isIn;

    private String productName;
    private String storeName;
    private String startTime;
    private String endTime;
}