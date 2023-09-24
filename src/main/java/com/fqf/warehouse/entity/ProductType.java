package com.fqf.warehouse.entity;

import java.io.Serializable;
import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductType implements Serializable {
    /**
     *
     */
    private Integer typeId;

    /**
     *
     */
    private Integer parentId;

    /**
     *
     */
    private String typeCode;

    /**
     *
     */
    private String typeName;

    /**
     *
     */
    private String typeDesc;

    private List<ProductType> childProductCategory;

}

