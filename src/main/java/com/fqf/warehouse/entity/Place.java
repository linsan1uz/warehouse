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
public class Place implements Serializable {
    /**
     *
     */
    private Integer placeId;

    /**
     *
     */
    private String placeName;

    /**
     *
     */
    private String placeNum;

    /**
     *
     */
    private String introduce;

    /**
     * 0:可用  1:不可用
     */
    private String isDelete;

}

