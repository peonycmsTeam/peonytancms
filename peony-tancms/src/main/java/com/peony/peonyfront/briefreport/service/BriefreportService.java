package com.peony.peonyfront.briefreport.service;

import java.util.List;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.briefreport.model.Briefreport;

public interface BriefreportService {
    /**
     * 查询用户配置的简报列表
     * 
     * @param briefreport
     * @return
     */
    Pagination<Briefreport> selectByPage(Briefreport briefreport);

    List<Briefreport> findAll(Briefreport briefreport);

    /**
     * 根据id删除简报
     */
    int deleteByPrimaryKey(Integer briefreportId);

    /**
     * 保存简报
     * 
     * @param briefreport
     * @return
     */
    int insertSelective(Briefreport briefreport);

    /**
     * 根据id查询简报信息
     */
    Briefreport selectByPrimaryKey(Integer briefreportId);

    /**
     * 更新简报
     */
    int updateByPrimaryKeySelective(Briefreport briefreport);
}
