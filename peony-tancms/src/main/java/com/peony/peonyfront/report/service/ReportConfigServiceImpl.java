package com.peony.peonyfront.report.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.report.dao.ReportConfigMapper;
import com.peony.peonyfront.report.model.ReportConfig;
import com.peony.peonyfront.util.log.Action;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

/**
 * @author jhj 日报配置
 */
@Service
public class ReportConfigServiceImpl implements ReportConfigService {
    @Resource
    private ReportConfigMapper reportConfigMapper;

    @Override
    public int deleteByPrimaryKey(Integer reportConfigId) {
        return this.reportConfigMapper.deleteByPrimaryKey(reportConfigId);
    }

    @Override
    public int insert(ReportConfig record) {
        return this.reportConfigMapper.insert(record);
    }

    @Override
    public int insertSelective(ReportConfig record) {
        return this.reportConfigMapper.insertSelective(record);
    }

    @Override
    public ReportConfig selectByPrimaryKey(Integer reportConfigId) {
        return this.reportConfigMapper.selectByPrimaryKey(reportConfigId);
    }

    @Override
    @Action(description = "修改日报模板定制", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.UPDATE)
    public int updateByPrimaryKeySelective(ReportConfig record) {
        return this.reportConfigMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Action(description = "修改日报模板定制", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.UPDATE)
    public int updateByPrimaryKey(ReportConfig record) {
        return this.reportConfigMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ReportConfig> selectReportConfigByUserId(Integer userId) {
        return this.reportConfigMapper.selectReportConfigByUserId(userId);
    }

}
