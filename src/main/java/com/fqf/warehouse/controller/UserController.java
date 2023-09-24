package com.fqf.warehouse.controller;

import com.fqf.warehouse.dto.AssignRoleDto;
import com.fqf.warehouse.entity.*;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.RoleService;
import com.fqf.warehouse.service.UserRoleSevice;
import com.fqf.warehouse.service.UserService;
import com.fqf.warehouse.utils.DigestUtil;
import com.fqf.warehouse.utils.TokenUtils;
import com.fqf.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    //注入UserService
    @Autowired
    private UserService userService;

    @RequestMapping("/user-list")
    public Result userList(User user, Page page){
        page = userService.queryUserByPage(page,user);
        return Result.ok(page);
    }

    /*
    *
    *添加用户id
    *
    * */
    @Autowired
    private TokenUtils tokenUtils;
    @RequestMapping("addUser")
    public Result addUser(@RequestBody User user, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        //拿到当前的用户id
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();
        user.setCreateBy(createBy);


        //执行业务
        Result result = userService.saveUser(user);
        return result;
    }
    /*


    */
    @RequestMapping("updateState")
    public Result updateUserState(@RequestBody User user){
        Result result = userService.setUserState(user);
        //响应直接返回结果对象
        return result;
    }
    @Autowired
    private RoleService roleSevice;

    //参数@PathVariable Integer userId --表示将路径占位符的userId的值 赋值给请求处理方法入参变量的userId;
    @RequestMapping("/user-role-list/{userId}")
    public Result userRoleList(@PathVariable Integer userId){
        List<Role> roles = roleSevice.queryUserRoleByUid(userId);
        return Result.ok(roles);
    }

    @RequestMapping("assignRole")
    public Result assignRole(@RequestBody AssignRoleDto assignRoleDto){
        userService.assignRole(assignRoleDto);
        return Result.ok("分配成功");
    }
    @RequestMapping("deleteUser/{userId}")
    public Result deleteUser(@PathVariable Integer userId){
        Result result = userService.deleteUser(Arrays.asList(userId));
        return result;
    }
    @RequestMapping("/deleteUserList")
    public Result deleteUserList(@RequestBody List<Integer> userIdList){
        Result result = userService.deleteUser(userIdList);
        return result;
    }
    @RequestMapping("updateUser")
    public Result updateUser(@RequestBody User user,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        user.setUpdateBy(userId);
        Result result = userService.setUserById(user);
        return result;
    }
    @RequestMapping("updatePwd/{userId}")
    public Result updatePwd(@PathVariable Integer userId,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int currentUserUserId = currentUser.getUserId();
        User user = userService.queryUserById(userId);
        user.setUpdateBy(currentUserUserId);
        Result result = userService.setUserPwdById(user);
        return result;
    }

}
