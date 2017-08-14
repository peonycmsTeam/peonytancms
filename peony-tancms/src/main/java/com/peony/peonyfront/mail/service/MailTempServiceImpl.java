package com.peony.peonyfront.mail.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.mail.dao.MailTempMapper;
import com.peony.peonyfront.mail.model.MailTemp;

/**
 * 邮件模板
 * 
 * @author jhj
 */
@Service
public class MailTempServiceImpl implements MailTempService {

    @Resource
    private MailTempMapper mailTempMapper;

    @Override
    public int deleteByPrimaryKey(Integer mailTempId) {
        return this.mailTempMapper.deleteByPrimaryKey(mailTempId);
    }

    @Override
    public int insert(MailTemp record) {
        return this.mailTempMapper.insert(record);
    }

    @Override
    public int insertSelective(MailTemp record) {
        return this.mailTempMapper.insertSelective(record);
    }

    @Override
    public MailTemp selectByPrimaryKey(Integer mailTempId) {
        return this.mailTempMapper.selectByPrimaryKey(mailTempId);
    }

    @Override
    public int updateByPrimaryKeySelective(MailTemp record) {
        return this.mailTempMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MailTemp record) {
        return this.mailTempMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MailTemp> selectMailTemps(MailTemp record) {
        return this.mailTempMapper.selectMailTemps(record);
    }

    @Override
    public MailTemp findTemplateByUserId(Integer userId) {
        MailTemp mailTemp = new MailTemp();
        mailTemp.setUserId(userId);
        return this.mailTempMapper.findTemplateByUserId(mailTemp);
    }
}
