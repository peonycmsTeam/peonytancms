package com.peony.peonyfront.login.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.login.dao.MenuMapper;
import com.peony.peonyfront.login.model.Menu;

/**
 * 菜单服务
 * 
 * @author jhj
 * @date 2014-5-8 下午3:38:07
 */

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public Menu selectByPrimaryKey(Integer menuId) {
        return this.menuMapper.selectByPrimaryKey(menuId);
    }

    @Override
    public List<Menu> selectMenus() {
        return this.menuMapper.selectMenus();
    }

    @Override
    public List<Menu> selectByRoleId(Integer roleId) {
        return this.menuMapper.selectByRoleId(roleId);
    }

    @Override
    public List<Menu> selectByMenuCode(Menu record) {
        return this.menuMapper.selectByMenuCode(record);
    }

    @Override
    public List<Menu> selectAllMenus() {
        return this.menuMapper.selectAllMenus();
    }
}
