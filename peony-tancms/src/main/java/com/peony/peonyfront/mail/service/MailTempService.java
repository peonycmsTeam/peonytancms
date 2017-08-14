package com.peony.peonyfront.mail.service;

import java.util.List;

import com.peony.peonyfront.mail.model.MailTemp;

public interface MailTempService {
    int deleteByPrimaryKey(Integer mailTempId);

    int insert(MailTemp record);

    int insertSelective(MailTemp record);

    MailTemp selectByPrimaryKey(Integer mailTempId);

    int updateByPrimaryKeySelective(MailTemp record);

    int updateByPrimaryKey(MailTemp record);

    List<MailTemp> selectMailTemps(MailTemp record);

    /**
     * 根据用户id查询用户所定义的模板
     * 
     * @param record
     * @return
     */
    MailTemp findTemplateByUserId(Integer userId);
}
