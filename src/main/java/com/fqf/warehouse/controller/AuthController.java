package com.fqf.warehouse.controller;

import com.fqf.warehouse.entity.Auth;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;
    @RequestMapping("/auth-tree")
    public Result allAuthTree(){
        List<Auth> authTree = authService.queryAuthTreeByRid();
        return Result.ok(authTree);
    }
}
