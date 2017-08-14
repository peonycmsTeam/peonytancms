package com.peony.peonyfront.officeplatform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.officeplatform.dao.MailMapper;
import com.peony.peonyfront.officeplatform.model.Mail;

@Service
public class MailServiceImpl implements MailService {
    @Resource
    private MailMapper mailMapper;

    @Override
    public Pagination<Mail> selectMailByPage(Mail mail) {
        List<Mail> mails = this.mailMapper.selectMailByPage(mail);
        return new Pagination<Mail>(mails, mail.getPageParameter());
    }

    @Override
    public int delMailByMailIds(String[] mailIds) {
        return this.mailMapper.delMailByMailIds(mailIds);
    }

    @Override
    public int insertSelective(Mail record) {
        return this.mailMapper.insertSelective(record);
    }

    @Override
    public int selectMailTodayCount(Integer userId) {
        return this.mailMapper.selectMailTodayCount(userId);
    }

}
