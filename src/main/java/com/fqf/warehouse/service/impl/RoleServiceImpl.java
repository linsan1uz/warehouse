package com.fqf.warehouse.service.impl;

import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.entity.Role;
import com.fqf.warehouse.mapper.AuthMapper;
import com.fqf.warehouse.mapper.RoleMapper;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//指定缓存的名称 数据保存到redis中的键的前缀 一般值给标注@CacheConfig注解的类的全路径
@CacheConfig(cacheNames="com.fqf.warehouse.service.impl.RoleServiceImpl")
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Cacheable(key = "'all:role'")
    @Override
    public List<Role> queryAllRole() {
        return roleMapper.findAllRole();
    }

    @Override
    public List<Role> queryUserRoleByUid(Integer uid) {
        return roleMapper.findUserRoleByUid(uid);
    }

    @Override
    public Page queryRoleByPage(Page page, Role role) {
        Integer roleRowCount = roleMapper.findRoleRowCount(role);
        List<Role> roleList = roleMapper.findRoleByPage(role, page);
        page.setTotalNum(roleRowCount);
        page.setResultList(roleList);
        return page;
    }

    @Override
    public Result addRole(Role role) {
        Integer roleId = roleMapper.findRoleIdByName(role.getRoleName());
        if (roleId!=null){
            return Result.err(Result.CODE_ERR_BUSINESS,"角色重复");
        }
        Integer i = roleMapper.addRole(role);
        if (i>0){
            return Result.ok("添加角色成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"添加角色失败");
    }

    @Override
    public Result updateState(Role role) {
        Integer i = roleMapper.setRoleState(role.getRoleId(),role.getRoleState());
        if (i>0){
            return Result.ok("启用或禁用成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"启用或禁用失败");
    }

    @Override
    public List<Integer> queryAuthIds(Integer roleId) {
        return roleMapper.findAuthIds(roleId);
    }
    @Autowired
    private AuthMapper authMapper;
    @Transactional
    @Override
    public void deleteRole(Integer roleId) {
        Integer i = roleMapper.deleteRoleById(roleId);
        if (i>0){
            authMapper.removeAuthById(roleId);
        }
    }

    @Override
    public Result updateRoleDesc(Role role) {
        Integer i = roleMapper.setRoleDesc(role);
        if (i>0){
            return Result.ok("角色修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"角色修改失败");
    }
}
