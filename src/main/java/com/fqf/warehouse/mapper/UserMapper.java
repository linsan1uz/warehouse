package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.User;
import com.fqf.warehouse.page.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    public User findUserByCode(String usercode);

    public User findUserById(Integer userId);
    //分页查询的方法
    public List<User> findUserByPage(@Param("user") User user, @Param("page")Page page);
    //查询用户的方法
    public Integer findUserRowCount(User user);
    //添加用户的方法
    public Integer insertUser(User user);
    //根据用户id修改用户状态
    public Integer setStateByUid(Integer userId,String userState);
    public Integer deleteUser(List<Integer> userIdList);
    public int setUserNameById(User user);
    public int setUserPwdById(@Param("user") User user,@Param("password") String password);
}
