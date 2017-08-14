package com.peony.peonyfront.login.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.login.dao.UserMapper;
import com.peony.peonyfront.login.model.User;

/**
 * 用户服务接口
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper; // 用户mapper

    /**
     * p 根据id加载单个用户
     *
     * @param id
     * @return
     */
    @Override
    public User selectByPrimaryKey(Integer id) {
        User user = this.userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public List<User> selectUsers(User user) {
        return this.userMapper.selectUsers(user);
    }

    /**
     * 根据用户登录名查询 param loginName
     */
    @Override
    public List<User> selectUserByLoginName(String loginName) {
        return this.userMapper.selectUserByLoginName(loginName);
    }

    @Override
    public List<User> selectByRoleIdNoPage(User user) {
        return this.userMapper.selectByRoleIdPage(user);
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        return this.userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateUserStyle(User user) {
        return this.userMapper.updateUserStyle(user);
    }
}
