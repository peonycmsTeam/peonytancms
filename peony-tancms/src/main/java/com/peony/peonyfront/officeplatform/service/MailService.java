package com.peony.peonyfront.officeplatform.service;

import org.apache.ibatis.annotations.Param;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.officeplatform.model.Mail;

public interface MailService {
    /**
     * 收件箱分页
     * 
     * @param record
     * @return
     */
    Pagination<Mail> selectMailByPage(Mail record);

    /**
     * 批量删除邮件
     * 
     * @param mailIds
     * @return
     */
    int delMailByMailIds(@Param(value = "mailIds") String[] mailIds);

    /**
     * 保存已发送的邮件
     * 
     * @param record
     * @return
     */
    int insertSelective(Mail record);

    /**
     * 发送邮件总数
     * 
     * @param userId
     * @return
     */
    int selectMailTodayCount(@Param(value = "userId") Integer userId);
}
