package com.peony.peonyfront.officeplatform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.officeplatform.model.Mail;

public interface MailMapper {
    int deleteByPrimaryKey(Integer mailId);

    int insert(Mail record);

    int insertSelective(Mail record);

    Mail selectByPrimaryKey(Integer mailId);

    int updateByPrimaryKeySelective(Mail record);

    int updateByPrimaryKey(Mail record);

    /**
     * 收件箱分页
     * 
     * @param record
     * @return
     */
    List<Mail> selectMailByPage(Mail record);

    /**
     * 批量删除邮件
     * 
     * @param mailIds
     * @return
     */
    int delMailByMailIds(@Param(value = "mailIds") String[] mailIds);

    /**
     * 发送邮件总数
     * 
     * @param userId
     * @return
     */
    int selectMailTodayCount(@Param(value = "userId") Integer userId);
}