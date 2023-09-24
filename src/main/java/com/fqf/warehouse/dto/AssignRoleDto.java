package com.fqf.warehouse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
//分配角色请求josn的dto类
public class AssignRoleDto {
    private Integer userId;
    private List<String> roleCheckList;
}
