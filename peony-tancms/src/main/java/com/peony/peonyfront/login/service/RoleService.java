package com.peony.peonyfront.login.service;

import java.util.List;

import com.peony.peonyfront.login.model.Role;

/**
 * 角色服务
 */
public interface RoleService {

    /**
     * 角色列表无分页
     */
    public List<Role> selectRoles();

    /**
     * 根据主键查询
     * 
     * @param userId
     * @return
     */
    Role selectByPrimaryKey(Integer Id);

    /**
     * 通过userId查询用角色不分页
     * 
     * @param userId
     * @return
     */
    List<Role> selectByUserId(Integer userId);
}
