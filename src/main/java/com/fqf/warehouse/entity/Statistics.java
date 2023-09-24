package com.fqf.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {
    private Integer storeId;
    private String storeName;
    private Integer totalInvent;
}
