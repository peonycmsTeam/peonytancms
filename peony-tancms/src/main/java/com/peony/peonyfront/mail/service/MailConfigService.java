package com.peony.peonyfront.mail.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.mail.model.MailConfig;

/**
 * 邮件配置服务
 * 
 * @author jhj
 */
public interface MailConfigService {
    int deleteByPrimaryKey(Integer mailConfigId);

    int deleteByUserId(Integer userId);

    int insert(MailConfig record);

    int insertSelective(MailConfig record);

    MailConfig selectByPrimaryKey(Integer mailConfigId);

    int updateByPrimaryKeySelective(MailConfig record);

    int updateByPrimaryKey(MailConfig record);

    List<MailConfig> selectMailConfigByUserId(Integer userId);
}
