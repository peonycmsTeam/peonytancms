package com.peony.peonyfront.mail.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.mail.model.MailConfig;

public interface MailConfigMapper {
    int deleteByPrimaryKey(Integer mailConfigId);

    int deleteByUserId(@Param(value = "userId") Integer userId);

    int insert(MailConfig record);

    int insertSelective(MailConfig record);

    MailConfig selectByPrimaryKey(Integer mailConfigId);

    int updateByPrimaryKeySelective(MailConfig record);

    int updateByPrimaryKey(MailConfig record);

    List<MailConfig> selectMailConfigByUserId(@Param(value = "userId") Integer userId);
}