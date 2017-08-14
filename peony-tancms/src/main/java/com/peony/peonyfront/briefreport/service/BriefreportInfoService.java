package com.peony.peonyfront.briefreport.service;

import java.util.List;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.briefreport.model.Briefreport;
import com.peony.peonyfront.briefreport.model.BriefreportInfo;

public interface BriefreportInfoService {
    /**
     * 根据简报id查询出简报列表
     * 
     * @param briefreportInfo
     * @return
     */
    Pagination<BriefreportInfo> selectBriefreportInfoByBreiefreportIdByPage(BriefreportInfo briefreportInfo);

    /**
     * 更新
     * 
     * @param briefreportInfo
     * @return
     */
    int updateByPrimaryKeySelective(BriefreportInfo briefreportInfo);

    /**
     * 根据briefreportInfoId查询
     * 
     * @param briefreportInfoId
     * @return
     */
    BriefreportInfo selectByPrimaryKey(Integer briefreportInfoId);

    /**
     * 删除选中的简报信息
     * 
     * @param briefreportInfoIds
     * @return
     */
    int delBriefreportInfoByBriefreportInfoIds(String[] briefreportInfoIds);

    /**
     * 添加
     * 
     * @param briefreportInfo
     * @return
     */
    int insertSelective(BriefreportInfo briefreportInfo);

    /**
     * 根据简报id查询出简报列表(无分页)
     * 
     * @param briefreportInfo
     * @return
     */
    List<BriefreportInfo> selectBriefreportInfoByBreiefreportId(BriefreportInfo briefreportInfo);

    /**
     * 得到简报下的行业舆情节点 (行业舆情)
     * 
     * @param briefreportInfo
     * @return
     */
    List<BriefreportInfo> selectEventByBreiefreportId(BriefreportInfo briefreportInfo);

    /**
     * 得到简报下的定制舆情节点 (行业舆情)
     * 
     * @param briefreportInfo
     * @return
     */
    List<BriefreportInfo> selectSubjectByBreiefreportId(BriefreportInfo briefreportInfo);
}
