package com.fqf.warehouse.service.impl;

import com.fqf.warehouse.dto.AssignRoleDto;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.entity.User;
import com.fqf.warehouse.entity.UserRole;
import com.fqf.warehouse.mapper.RoleMapper;
import com.fqf.warehouse.mapper.UserMapper;
import com.fqf.warehouse.mapper.UserRoleMapper;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.UserService;
import com.fqf.warehouse.utils.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByCode(String userCode) {
        return userMapper.findUserByCode(userCode);
    }

    @Override
    public User queryUserById(Integer userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public Page queryUserByPage(Page page, User user) {
        Integer userRowCount = userMapper.findUserRowCount(user);
        List<User> userList = userMapper.findUserByPage(user, page);
        //组装所有分页信息
        page.setTotalNum(userRowCount);
        page.setResultList(userList);
        return page;
    }

    @Override
    public Result saveUser(User user) {
        //判断账号是否存在
        User u = userMapper.findUserByCode(user.getUserCode());
        if (u!=null){
            return Result.err(Result.CODE_ERR_BUSINESS,"账号重复");
        }
        //对密码做加密处理
        String password = DigestUtil.hmacSign(user.getUserPwd());
        user.setUserPwd(password);
        Integer i = userMapper.insertUser(user);
        if (i>0){
            return Result.ok("用户添加成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"用户添加失败");
    }

    @Override
    public Result setUserState(User user) {
        Integer i = userMapper.setStateByUid(user.getUserId(), user.getUserState());
        if (i>0){
            return Result.ok("启用或禁用成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"启用或禁用失败");
    }
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Transactional
    @Override
    public void assignRole(AssignRoleDto assignRoleDto) {
        userRoleMapper.removeUserRoleByUid(assignRoleDto.getUserId());

        List<String> roleNameList = assignRoleDto.getRoleCheckList();
        for (String roleName:roleNameList) {
            Integer roleId = roleMapper.findRoleIdByName(roleName);
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserRoleId(assignRoleDto.getUserId());
            userRoleMapper.insertUserRole(userRole);
        }
    }

    @Override
    public Result deleteUser(List<Integer> userIdList) {
        Integer i = userMapper.deleteUser(userIdList);
        if (i>0){
            return Result.ok("删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"删除失败");
        //userRoleMapper.removeUserRoleByUid(userIdList);
    }

    @Override
    public Result setUserById(User user) {
        int i = userMapper.setUserNameById(user);
        if (i>0){
            return Result.ok("修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"修改失败");
    }

    @Override
    public Result setUserPwdById(User user) {
        String password = DigestUtil.hmacSign("123456");
        int i = userMapper.setUserPwdById(user,password);
        if (i>0){
            return Result.ok("修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"修改失败");
    }
}
