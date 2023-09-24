package com.fqf.warehouse.service;

import com.fqf.warehouse.dto.AssignRoleDto;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.entity.User;
import com.fqf.warehouse.page.Page;

import java.util.List;

public interface UserService {

     public User queryUserByCode(String userCode);
     public User queryUserById(Integer userId);
     public Page queryUserByPage(Page page,User user);
     public Result saveUser(User user);
     public Result setUserState(User user);
     //给用户分配角色的方法
     public void assignRole(AssignRoleDto assignRoleDto);
     public Result deleteUser(List<Integer> userIdList);
     //修改业务对象方法
     public Result setUserById(User user);
     public Result setUserPwdById(User user);
}
