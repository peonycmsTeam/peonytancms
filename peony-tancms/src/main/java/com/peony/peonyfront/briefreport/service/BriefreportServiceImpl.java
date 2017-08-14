package com.peony.peonyfront.briefreport.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.briefreport.dao.BriefreportInfoMapper;
import com.peony.peonyfront.briefreport.dao.BriefreportMapper;
import com.peony.peonyfront.briefreport.model.Briefreport;
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.util.log.Action;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

@Service
public class BriefreportServiceImpl implements BriefreportService {
    @Resource
    private IdService             idService;            // id服务接口
    @Resource
    private BriefreportMapper     briefreportMapper;
    @Resource
    private BriefreportInfoMapper briefreportInfoMapper;

    @Override
    public Pagination<Briefreport> selectByPage(Briefreport briefreport) {
        List<Briefreport> briefreports = this.briefreportMapper.selectByPage(briefreport);
        return new Pagination<Briefreport>(briefreports, briefreport.getPageParameter());
    }

    @Override
    @Action(description = "删除舆情简报", operateMode = OperateMode.舆情简报, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.DELETE)
    public int deleteByPrimaryKey(Integer briefreportId) {
        this.briefreportInfoMapper.delBriefreportInfoByBriefreportId(briefreportId);
        return this.briefreportMapper.deleteByPrimaryKey(briefreportId);
    }

    @Override
    @Action(description = "新增舆情简报", operateMode = OperateMode.舆情简报, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.SAVE)
    public int insertSelective(Briefreport briefreport) {
        briefreport.setBriefreportId(this.idService.NextKey("briefreportId"));
        return this.briefreportMapper.insertSelective(briefreport);
    }

    @Override
    public Briefreport selectByPrimaryKey(Integer briefreportId) {
        return this.briefreportMapper.selectByPrimaryKey(briefreportId);
    }

    @Override
    @Action(description = "修改舆情简报", operateMode = OperateMode.舆情简报, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.UPDATE)
    public int updateByPrimaryKeySelective(Briefreport briefreport) {
        return this.briefreportMapper.updateByPrimaryKeySelective(briefreport);
    }

    @Override
    public List<Briefreport> findAll(Briefreport briefreport) {
        return this.briefreportMapper.selectAll(briefreport);
    }

}
