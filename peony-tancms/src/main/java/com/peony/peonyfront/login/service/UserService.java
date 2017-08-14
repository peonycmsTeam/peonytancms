package com.peony.peonyfront.login.service;

import java.util.List;

import com.peony.peonyfront.login.model.User;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 根据登录名查询
     * 
     * @param loginName
     * @return
     */
    public List<User> selectUserByLoginName(String loginName);

    /**
     * 用户列表无分页
     */
    public List<User> selectUsers(User user);

    /**
     * 通过roleId查询用户不 分页
     * 
     * @param roleId
     * @return
     */
    List<User> selectByRoleIdNoPage(User user);

    /**
     * 根据主键查询
     * 
     * @param userId
     * @return
     */
    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User user);

    int updateUserStyle(User user);
}
