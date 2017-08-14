package com.peony.peonyfront.login.service;

import java.util.List;

import com.peony.peonyfront.login.model.Menu;

/**
 * 菜单服务接口
 * 
 * @author jhj
 * @date 2014-5-8 下午3:37:36
 */
public interface MenuService {

    /**
     * 根据主键查询
     * 
     * @param deptId
     * @return
     */
    Menu selectByPrimaryKey(Integer deptId);

    /**
     * 查询可见菜单
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
