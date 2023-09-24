package com.fqf.warehouse.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Unit implements Serializable {
    /**
     *
     */
    private Integer unitId;

    /**
     *
     */
    private String unitName;

    /**
     *
     */
    private String unitDesc;

}

