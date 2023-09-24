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
public class Supply implements Serializable {
    /**
     *
     */
    private Integer supplyId;

    /**
     *
     */
    private String supplyNum;

    /**
     *
     */
    private String supplyName;

    /**
     *
     */
    private String supplyIntroduce;

    /**
     *
     */
    private String concat;

    /**
     *
     */
    private String phone;

    /**
     *
     */
    private String address;

    /**
     * 0:可用  1:不可用
     */
    private String isDelete;

    private static final long serialVersionUID = 1L;
}

