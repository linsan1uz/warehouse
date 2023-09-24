package com.fqf.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * role
 * @author 
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Role implements Serializable {
    private Integer roleId;

    private String roleName;

    private String roleDesc;

    private String roleCode;

    /**
     * 1 启用 0 禁用
     */
    private String roleState;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

}