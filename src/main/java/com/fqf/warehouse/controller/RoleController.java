package com.fqf.warehouse.controller;

import com.fqf.warehouse.dto.AssignAuthDto;
import com.fqf.warehouse.entity.CurrentUser;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.entity.Role;
import com.fqf.warehouse.entity.User;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.AuthService;
import com.fqf.warehouse.service.RoleService;
import com.fqf.warehouse.utils.TokenUtils;
import com.fqf.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private TokenUtils tokenUtils;
    @RequestMapping("/role-list")
    public Result roleList(){
        List<Role> rolesList = roleService.queryAllRole();
        return Result.ok(rolesList);
    }
    @RequestMapping("/role-page-list")
    public Result rolePageList(Role role, Page page){
        page = roleService.queryRoleByPage(page, role);
        return Result.ok(page);
    }
    @RequestMapping("/role-add")
    public Result addRole(@RequestBody Role role,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();
        role.setCreateBy(createBy);
        Result result = roleService.addRole(role);
        return result;
    }
    @RequestMapping("role-state-update")
    public Result updateRoleState(@RequestBody Role role){
        Result result = roleService.updateState(role);
        return result;
    }
    @RequestMapping("role-auth")
    public Result roleAuth(Integer roleId){
        List<Integer> authIdList = roleService.queryAuthIds(roleId);
        return Result.ok(authIdList);
    }
    @Autowired
    private AuthService authService;
    @RequestMapping("auth-grant")
    public Result authGrant(@RequestBody AssignAuthDto assignAuthDto){
        authService.assignAuth(assignAuthDto);
        return Result.ok("分配成功");
    }

    @RequestMapping("role-delete/{roleId}")
    public Result deleteRole(@PathVariable Integer roleId){
        roleService.deleteRole(roleId);
        return Result.ok("删除成功");
    }
    @RequestMapping("role-update")
    public Result updateRole(@RequestBody Role role,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int updateBy = currentUser.getUserId();
        role.setCreateBy(updateBy);
        Result result = roleService.updateRoleDesc(role);
        return result;
    }
}
