package com.peony.peonyfront.login.dao;

import java.util.List;
import com.peony.peonyfront.login.model.Role;

public interface RoleMapper {

    /**
     * 根据主键查询
     * 
     * @param Id
     * @return
     */
    Role selectByPrimaryKey(Integer Id);

    /**
     * 查询列表 不分页
     * 
     * @param record
     * @return
     */
    List<Role> selectRoles();

    /**
     * 通过userId查询用角色分页
     * 
     * @param userId
     * @return
     */
    List<Role> selectByUserId(Integer userId);

}