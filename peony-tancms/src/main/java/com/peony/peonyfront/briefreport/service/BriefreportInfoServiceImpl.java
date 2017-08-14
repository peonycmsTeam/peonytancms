package com.peony.peonyfront.briefreport.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.briefreport.dao.BriefreportInfoCntMapper;
import com.peony.peonyfront.briefreport.dao.BriefreportInfoMapper;
import com.peony.peonyfront.briefreport.model.BriefreportInfo;
import com.peony.peonyfront.util.log.Action;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

@Service
public class BriefreportInfoServiceImpl implements BriefreportInfoService {
    @Resource
    private BriefreportInfoMapper    briefreportInfoMapper;
    @Resource
    private BriefreportInfoCntMapper BriefreportInfoCntMapper;

    @Override
    public Pagination<BriefreportInfo> selectBriefreportInfoByBreiefreportIdByPage(BriefreportInfo briefreportInfo) {
        List<BriefreportInfo> LBriefreportInfo = this.briefreportInfoMapper.selectBriefreportInfoByBreiefreportIdByPage(briefreportInfo);
        return new Pagination<BriefreportInfo>(LBriefreportInfo, briefreportInfo.getPageParameter());
    }

    @Override
    public int updateByPrimaryKeySelective(BriefreportInfo briefreportInfo) {
        return this.briefreportInfoMapper.updateByPrimaryKeySelective(briefreportInfo);
    }

    @Override
    public BriefreportInfo selectByPrimaryKey(Integer briefreportInfoId) {
        return this.briefreportInfoMapper.selectByPrimaryKey(briefreportInfoId);
    }

    @Override
    @Action(description = "删除简报", operateMode = OperateMode.舆情简报, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.DELETE)
    public int delBriefreportInfoByBriefreportInfoIds(String[] briefreportInfoIds) {
        try {
            this.briefreportInfoMapper.delBriefreportInfoByBriefreportInfoIds(briefreportInfoIds);
            this.BriefreportInfoCntMapper.delBriefreportInfoCntByBriefreportInfoIds(briefreportInfoIds);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    @Action(description = "加入简报", operateMode = OperateMode.舆情简报, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.SAVE)
    public int insertSelective(BriefreportInfo briefreportInfo) {
        return this.briefreportInfoMapper.insertSelective(briefreportInfo);
    }

    @Override
    public List<BriefreportInfo> selectBriefreportInfoByBreiefreportId(BriefreportInfo briefreportInfo) {
        return this.briefreportInfoMapper.selectBriefreportInfoByBreiefreportId(briefreportInfo);
    }

    @Override
    public List<BriefreportInfo> selectEventByBreiefreportId(BriefreportInfo briefreportInfo) {
        return this.briefreportInfoMapper.selectEventByBreiefreportId(briefreportInfo);
    }

    @Override
    public List<BriefreportInfo> selectSubjectByBreiefreportId(BriefreportInfo briefreportInfo) {
        return this.briefreportInfoMapper.selectSubjectByBreiefreportId(briefreportInfo);
    }
}
