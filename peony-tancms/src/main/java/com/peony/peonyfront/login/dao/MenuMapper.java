package com.peony.peonyfront.login.dao;

import java.util.List;

import com.peony.peonyfront.login.model.Menu;

public interface MenuMapper {
    /**
     * 根据主键查询
     * 
     * @param deptId
     * @return
     */
    Menu selectByPrimaryKey(Integer deptId);

    /**
     * 查询可见的菜单列表
     * 
     * @return
     */
    List<Menu> selectMenus();

    /**
     * 查询全部菜单列表
     * 
     * @return
     */
    List<Menu> selectAllMenus();

    /**
     * 根据roleId加载menus
     * 
     * @param roleId
     * @return
     */
    List<Menu> selectByRoleId(Integer roleId);

    /**
     * 根据code获取menu list
     * 
     * @param code
     * @return
     */
    List<Menu> selectByMenuCode(Menu record);
}