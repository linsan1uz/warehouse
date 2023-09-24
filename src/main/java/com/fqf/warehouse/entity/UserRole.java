package com.fqf.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 用户角色表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    private Integer userRoleId;

    private Integer roleId;

    private Integer userId;
}