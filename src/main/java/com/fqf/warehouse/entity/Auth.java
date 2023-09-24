package com.fqf.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * auth_info
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Auth implements Serializable {
    private Integer authId;

    /**
     * 父id为空或为0，表示一级权限
     */
    private Integer parentId;

    private String authName;

    private String authDesc;

    private Integer authGrade;

    /**
     * 1 模块 、2  列表、 3  按钮
     */
    private String authType;

    private String authUrl;

    private String authCode;

    private Integer authOrder;

    /**
     * 1 启用 、0 禁用
     */
    private String authState;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;


    //--------------------追加属性-----------------//
    //存放当前菜单下所有的子级菜单
    private List<Auth> childAuth;


}