package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.Auth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMapper {
    //根据用户id查询用户权限下所有菜单的方法
    public List<Auth> findAuthByUid(Integer userId);
    //角色权限下所有菜单的方法
    public List<Auth> findAllRoleAuth();
    public Integer removeAuthById(Integer authId);
    public void insertRoleAuth(Integer roleId,Integer authId);
}