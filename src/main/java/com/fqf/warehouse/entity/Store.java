package com.fqf.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
    * 仓库表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Store implements Serializable {
    private Integer storeId;

    private String storeName;

    private String storeNum;

    private String storeAddress;

    private String concat;

    private String phone;
}