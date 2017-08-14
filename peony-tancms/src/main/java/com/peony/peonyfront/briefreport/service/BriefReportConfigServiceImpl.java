package com.peony.peonyfront.briefreport.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.briefreport.dao.BriefReportConfigMapper;
import com.peony.peonyfront.briefreport.model.BriefReportConfig;
import com.peony.peonyfront.util.log.Action;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

/**
 * 简报模板配置
 * 
 * @author jhj
 */
@Service
public class BriefReportConfigServiceImpl implements BriefReportConfigService {
    @Resource
    private BriefReportConfigMapper briefReportConfigMapper;

    @Override
    public int deleteByPrimaryKey(Integer briefreportConfigId) {
        return this.briefReportConfigMapper.deleteByPrimaryKey(briefreportConfigId);
    }

    @Override
    public int insert(BriefReportConfig record) {
        return this.briefReportConfigMapper.insert(record);
    }

    @Override
    public int insertSelective(BriefReportConfig record) {
        return this.briefReportConfigMapper.insertSelective(record);
    }

    @Override
    public BriefReportConfig selectByPrimaryKey(Integer briefreportConfigId) {
        return this.briefReportConfigMapper.selectByPrimaryKey(briefreportConfigId);
    }

    @Override
    @Action(description = "修改简报模板定制", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.UPDATE)
    public int updateByPrimaryKeySelective(BriefReportConfig record) {
        return this.briefReportConfigMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Action(description = "修改简报模板定制", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.UPDATE)
    public int updateByPrimaryKey(BriefReportConfig record) {
        return this.briefReportConfigMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BriefReportConfig> selectBriefReportConfigByUserId(Integer userId) {
        return this.briefReportConfigMapper.selectBriefReportConfigByUserId(userId);
    }

    @Override
    public int deleteByUserId(Integer userId) {
        return this.briefReportConfigMapper.deleteByUserId(userId);
    }

}
