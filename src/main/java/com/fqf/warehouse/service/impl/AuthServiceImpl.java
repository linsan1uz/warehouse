package com.fqf.warehouse.service.impl;

import com.alibaba.fastjson.JSON;
import com.fqf.warehouse.dto.AssignAuthDto;
import com.fqf.warehouse.entity.Auth;
import com.fqf.warehouse.mapper.AuthMapper;
import com.fqf.warehouse.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;
    /*查询业务的方法
    *
    * 向redis缓存 -- 键 authTree:uid 值菜单树List<Auth>转json串
    *
    *
    * */
    @Override
    public List<Auth> queryAuthTreeByUid(Integer userId) {
        String authTreeJson = redisTemplate.opsForValue().get("authTree:" + userId);
        if (StringUtils.hasText(authTreeJson)){
            //菜单中有用户的缓存
            //将菜单树List<Auth>转的json串转回List<auth>并返回
            List<Auth> authTreeList = JSON.parseArray(authTreeJson, Auth.class);
            return authTreeList;
        }//说明redis中没有用户菜单树的缓存
        /*
        *
        * 查询用户权限下的所有菜单
        * */
        List<Auth> allAuthList = authMapper.findAuthByUid(userId);
        List<Auth> authTreeList = allAuthToAuthTree(allAuthList, 0);
        //向redis中缓存菜单树
        redisTemplate.opsForValue().set("auth:"+userId,JSON.toJSONString(authTreeList));
        return authTreeList;
    }

    @Override
    public List<Auth> queryAuthTreeByRid() {
        String authTreeJson = redisTemplate.opsForValue().get("all:authTree");
        if (StringUtils.hasText(authTreeJson)){
            //菜单中有用户的缓存
            //将菜单树List<Auth>转的json串转回List<auth>并返回
            List<Auth> authTreeList = JSON.parseArray(authTreeJson, Auth.class);
            return authTreeList;
        }//说明redis中没有用户菜单树的缓存
        /*
         *
         * 查询用户权限下的所有菜单
         * */
        List<Auth> allAuthList = authMapper.findAllRoleAuth();
        List<Auth> authTreeList = allAuthToAuthTree(allAuthList, 0);
        //向redis中缓存菜单树
        redisTemplate.opsForValue().set("all:authTree:",JSON.toJSONString(authTreeList));
        return authTreeList;
    }

    @Override
    public void assignAuth(AssignAuthDto assignAuthDto) {
        Integer roleId = assignAuthDto.getRoleId();
        List<Integer> authIds = assignAuthDto.getAuthIds();
        authMapper.removeAuthById(roleId);
        for (Integer authId: authIds) {
            authMapper.insertRoleAuth(roleId,authId);
        }
    }

    private List<Auth> allAuthToAuthTree(List<Auth> allAuthList,Integer pid){

        //查询出所有一级菜单
        List<Auth> authList = new ArrayList<>();
        for (Auth auth:allAuthList) {
            if (auth.getParentId().equals(pid)){
                authList.add(auth);
            }
        }
        //拿到一级菜单的二级
        for (Auth auth: authList) {
            List<Auth> childAuthList = allAuthToAuthTree(allAuthList, auth.getAuthId());
            auth.setChildAuth(childAuthList);
        }
        return authList;
    }
}
