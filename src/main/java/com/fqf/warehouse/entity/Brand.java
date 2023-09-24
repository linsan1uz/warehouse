package com.fqf.warehouse.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Brand implements Serializable {
    /**
     *
     */
    private Integer brandId;

    /**
     *
     */
    private String brandName;

    /**
     *
     */
    private String brandLeter;

    /**
     *
     */
    private String brandDesc;
}

