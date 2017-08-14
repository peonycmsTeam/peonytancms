package com.peony.peonyfront.briefreport.dao;

import java.util.List;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.attention.model.Attention;
import com.peony.peonyfront.briefreport.model.Briefreport;

public interface BriefreportMapper {
    int deleteByPrimaryKey(Integer briefreportId);

    int insert(Briefreport briefreport);

    int insertSelective(Briefreport briefreport);

    Briefreport selectByPrimaryKey(Integer briefreportId);

    int updateByPrimaryKeySelective(Briefreport briefreport);

    int updateByPrimaryKey(Briefreport briefreport);

    /**
     * 查询用户配置的简报列表
     * 
     * @param briefreport
     * @return
     */
    List<Briefreport> selectByPage(Briefreport briefreport);

    List<Briefreport> selectAll(Briefreport briefreport);

}