package com.peony.peonyfront.briefreport.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.briefreport.model.BriefreportInfo;

public interface BriefreportInfoMapper {
    int deleteByPrimaryKey(Integer briefreportInfoId);

    int insert(BriefreportInfo briefreportInfo);

    int insertSelective(BriefreportInfo briefreportInfo);

    BriefreportInfo selectByPrimaryKey(Integer briefreportInfoId);

    int updateByPrimaryKeySelective(BriefreportInfo briefreportInfo);

    int updateByPrimaryKey(BriefreportInfo briefreportInfo);

    /**
     * 根据简报id查询出简报列表
     * 
     * @param briefreportInfo
     * @return
     */
    List<BriefreportInfo> selectBriefreportInfoByBreiefreportIdByPage(BriefreportInfo briefreportInfo);

    /**
     * 删除选中的简报信息
     * 
     * @param briefreportInfoIds
     * @return
     */
    int delBriefreportInfoByBriefreportInfoIds(@Param(value = "briefreportInfoIds") String[] briefreportInfoIds);

    /**
     * 删除简报下的简报信息
     * 
     * @param briefreportId
     * @return
     */
    int delBriefreportInfoByBriefreportId(Integer briefreportId);

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