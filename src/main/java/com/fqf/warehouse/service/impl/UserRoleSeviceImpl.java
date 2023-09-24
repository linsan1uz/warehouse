package com.fqf.warehouse.service.impl;

import com.fqf.warehouse.entity.UserRole;
import com.fqf.warehouse.mapper.UserRoleMapper;
import com.fqf.warehouse.service.UserRoleSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserRoleSeviceImpl implements UserRoleSevice {
    @Autowired
    private UserRoleMapper userRoleMapper;
}
