package com.peony.peonyfront.login.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.common.utils.log.Action;
import com.peony.core.common.utils.log.OperateMode;
import com.peony.core.common.utils.log.OperateType;
import com.peony.peonyfront.login.dao.RoleMapper;
import com.peony.peonyfront.login.model.Role;

/**
 * 角色管理
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper; // 角色mapper

    /**
     * p 根据id加载单个用户
     * 
     * @param id
     * @return
     */
    @Override
    @Action(description = "根据id查询", operateMode = OperateMode.DEFAULT, operateType = OperateType.FIND)
    public Role selectByPrimaryKey(Integer id) {
        Role role = this.roleMapper.selectByPrimaryKey(id);
        return role;
    }

    @Override
    public List<Role> selectRoles() {
        return this.roleMapper.selectRoles();
    }

    @Override
    public List<Role> selectByUserId(Integer userId) {
        List<Role> list = this.roleMapper.selectByUserId(userId);
        return list;
    }

}