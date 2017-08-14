package com.peony.peonyfront.login.dao;

import java.util.List;

import com.peony.peonyfront.login.model.User;

/**
 * 用户dao接口
 *
 * @author lenovo41
 * @date 2014-5-9 下午4:08:25
 */
public interface UserMapper {
    /**
     *
     *
     * /** 根据主键查询
     *
     * @param userId
     * @return
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * 查询用户列表 不分页
     *
     * @param record
     * @return
     */
    List<User> selectUsers(User user);

    /**
     * 通过roleId查询用户 分页
     *
     * @param
     * @return
     */
    List<User> selectByRoleIdPage(User user);

    /**
     * 根据用户登录名查询
     *
     * @param loginName
     * @return
     */
    List<User> selectUserByLoginName(String loginName);

    int updateByPrimaryKeySelective(User user);

    /**
     * 修改用户样式
     *
     * @param user
     * @return
     */
    int updateUserStyle(User user);

}