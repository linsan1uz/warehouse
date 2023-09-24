package com.fqf.warehouse.controller;

import com.fqf.warehouse.entity.*;
import com.fqf.warehouse.service.AuthService;
import com.fqf.warehouse.service.UserService;
import com.fqf.warehouse.utils.DigestUtil;
import com.fqf.warehouse.utils.TokenUtils;
import com.fqf.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LoginController  {
    //注入

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @RequestMapping("/login")
    public Result login(@RequestBody LoginUser loginUser){

        //拿到客户端的验证码
        String code = loginUser.getVerificationCode();
        if(!redisTemplate.hasKey(code)){
            return Result.err(Result.CODE_ERR_BUSINESS,"验证码错误");
        }
        //根据账号查询
        User user = userService.queryUserByCode(loginUser.getUserCode());
        if(user!=null){
            //账号存在
            if(user.getUserState().equals(WarehouseConstants.USER_STATE_PASS)){
                //先拿到录入的密码
                String userPwd = loginUser.getUserPwd();
                userPwd= DigestUtil.hmacSign(userPwd);
                if (userPwd.equals(user.getUserPwd())){
                    //如果相等
                    CurrentUser currentUser =
                            new CurrentUser(user.getUserId(),user.getUserCode(),user.getUserName());
                    String token = tokenUtils.loginSign(currentUser, user.getUserPwd());
                    //把token颁发给客户端
                    return Result.ok("登录成功",token);
                }else {
                    return Result.err(Result.CODE_ERR_BUSINESS,"密码错误");
                }
            }else {
                return Result.err(Result.CODE_ERR_BUSINESS,"用户未审核");
            }
        }else{
            //账号不存在
            return Result.err(Result.CODE_ERR_BUSINESS,"账号不存在");
        }
    }
    /*
    获取当前用户信息的url--curr-user
    @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token
    表示请求头Token的值(前端的token赋值给请求处理方法入参变量token)
     */
    @RequestMapping("/curr-user")
    public Result currentUser(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        //解析token拿到封装了当前登录用户信息的currentUser对象
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        return Result.ok(currentUser);
    }
    @Autowired
    private AuthService authService;
    @RequestMapping("/user/auth-list")
    public Result loadAuthTree(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        //执行业务
        List<Auth> authTreeList = authService.queryAuthTreeByUid(userId);
        /*System.out.println("长度为:"+authTreeList.size());*/
        return Result.ok(authTreeList);
    }

    @DeleteMapping("/logout")
    public Result logout(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        redisTemplate.delete(token);
        return Result.ok();
    }
}
