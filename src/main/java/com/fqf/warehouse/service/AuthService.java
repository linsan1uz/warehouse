package com.fqf.warehouse.service;

import com.fqf.warehouse.dto.AssignAuthDto;
import com.fqf.warehouse.entity.Auth;
import com.fqf.warehouse.mapper.AuthMapper;

import java.util.List;

public interface AuthService {
    public List<Auth> queryAuthTreeByUid(Integer userId);
    public List<Auth> queryAuthTreeByRid();
    public void assignAuth(AssignAuthDto assignAuthDto);
}
