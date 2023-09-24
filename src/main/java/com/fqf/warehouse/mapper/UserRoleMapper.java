package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserRoleMapper {
    //根据用户id删除用户角色关系
   public Integer removeUserRoleByUid(Integer userId);
    public int insertUserRole(UserRole userRole);
}
