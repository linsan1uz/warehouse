package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.Role;
import com.fqf.warehouse.page.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    //查询所有角色
    public List<Role> findAllRole();
    //根据用户id查询用户分配的角色
    public List<Role> findUserRoleByUid(Integer userId);

    public Integer findRoleIdByName(String roleId);
    public List<Role> findRoleByPage(@Param("role") Role role, @Param("page") Page page);
    public Integer findRoleRowCount(Role role);
    public Integer addRole(Role role);
    public Integer setRoleState(Integer roleId,String roleState);
    public List<Integer> findAuthIds(Integer roleId);

    public Integer deleteRoleById(Integer roleId);

    public Integer setRoleDesc(Role role);
}
