package com.fqf.warehouse.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product implements Serializable {
    /**
     *
     */
    private Integer productId;

    /**
     *
     */
    private Integer storeId;

    /**
     *
     */
    private Integer brandId;

    /**
     *
     */
    private String productName;

    /**
     *
     */
    private String productNum;

    /**
     *
     */
    private Integer productInvent;

    /**
     *
     */
    private Integer typeId;

    /**
     *
     */
    private Integer supplyId;

    /**
     *
     */
    private Integer placeId;

    /**
     *
     */
    private Integer unitId;

    /**
     *
     */
    private String introduce;

    /**
     * 0 下架 1 上架
     */
    private String upDownState;

    /**
     *
     */
    private BigDecimal inPrice;

    /**
     *
     */
    private BigDecimal salePrice;

    /**
     *
     */
    private BigDecimal memPrice;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private Integer createBy;

    /**
     *
     */
    private Integer updateBy;

    /**
     *
     */
    private String imgs;

    /**
     *
     */
    private Date productDate;

    /**
     *
     */
    private Date suppDate;
    /*追加的属性*/
    private String brandName;

    private String supplyName;

    private String placeName;

    private String typeName;

    private Integer isOverDate;

    private String storeName;

    private String unitNam;
}

