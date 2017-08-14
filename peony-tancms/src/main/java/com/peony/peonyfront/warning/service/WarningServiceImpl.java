package com.peony.peonyfront.warning.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.util.log.Action;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;
import com.peony.peonyfront.warning.dao.WarningMapper;
import com.peony.peonyfront.warning.model.Warning;

@Service
public class WarningServiceImpl implements WarningService {
    @Resource
    private WarningMapper waringMapper;

    @Override
    public Pagination<Warning> selectWaringByPage(Warning warning) {
        List<Warning> warnings = this.waringMapper.selectWaringByPage(warning);
        return new Pagination<Warning>(warnings, warning.getPageParameter());
    }

    @Override
    @Action(description = "删除预警", operateMode = OperateMode.舆情预警, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.DELETE)
    public int delWaringByWaringIds(String[] waringIds) {
        try {
            this.waringMapper.delWaringByWaringIds(waringIds);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    @Action(description = "加入预警", operateMode = OperateMode.舆情预警, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.SAVE)
    public int saveSubjectPage(Warning warning) {
        return this.waringMapper.insertSelective(warning);
    }

    @Override
    public Warning selectByPrimaryKey(String warningId) {

        return this.waringMapper.selectByPrimaryKey(warningId);
    }

    @Override
    public int updateByPrimaryKeySelective(Warning record) {
        return this.waringMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int selectWarningTodayCount(Integer userId) {
        return this.waringMapper.selectWarningTodayCount(userId);
    }

    @Override
    public List<Warning> selectByWarningIds(String[] waringIds) {
        return this.waringMapper.selectByWarningIds(waringIds);
    }

    @Override
    public List<Warning> selectWaringByTime(Warning warning) {
        return this.waringMapper.selectWaringByTime(warning);
    }

}
