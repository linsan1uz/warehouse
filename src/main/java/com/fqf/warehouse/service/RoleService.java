package com.fqf.warehouse.service;

import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.entity.Role;

import java.io.LineNumberReader;
import java.util.List;

import com.fqf.warehouse.entity.User;
import com.fqf.warehouse.page.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

public interface RoleService {
    //查询所有角色的用户方法 全查的数据建议做缓存处理 但一个角色没有几个 可以不用
    public  List<Role>  queryAllRole();
    //根据用户id查询用户已分配角色的方法
    public List<Role> queryUserRoleByUid(Integer uid);
    public Page queryRoleByPage(Page page, Role role);

    public Result addRole(Role role);
    public Result updateState(Role role);
    public List<Integer> queryAuthIds(Integer roleId);
    public void deleteRole(Integer roleId);
    public Result updateRoleDesc(Role role);
}