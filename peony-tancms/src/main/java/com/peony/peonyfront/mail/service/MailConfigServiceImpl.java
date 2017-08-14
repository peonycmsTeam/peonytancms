package com.peony.peonyfront.mail.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.mail.dao.MailConfigMapper;
import com.peony.peonyfront.mail.model.MailConfig;
import com.peony.peonyfront.util.log.Action;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

/**
 * 邮件模板配置
 * 
 * @author jhj
 */
@Service
public class MailConfigServiceImpl implements MailConfigService {

    @Resource
    private MailConfigMapper mailConfigMapper;

    @Override
    public int deleteByPrimaryKey(Integer mailConfigId) {
        return this.mailConfigMapper.deleteByPrimaryKey(mailConfigId);
    }

    @Override
    public int insert(MailConfig record) {
        return this.mailConfigMapper.insert(record);
    }

    @Override
    public int insertSelective(MailConfig record) {
        return this.mailConfigMapper.insertSelective(record);
    }

    @Override
    public MailConfig selectByPrimaryKey(Integer mailConfigId) {
        return this.mailConfigMapper.selectByPrimaryKey(mailConfigId);
    }

    @Override
    @Action(description = "修改邮件模板定制", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.UPDATE)
    public int updateByPrimaryKeySelective(MailConfig record) {
        return this.mailConfigMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Action(description = "修改邮件模板定制", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.UPDATE)
    public int updateByPrimaryKey(MailConfig record) {
        return this.mailConfigMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MailConfig> selectMailConfigByUserId(Integer userId) {
        return this.mailConfigMapper.selectMailConfigByUserId(userId);
    }

    @Override
    public int deleteByUserId(Integer userId) {
        return this.mailConfigMapper.deleteByUserId(userId);
    }

}
