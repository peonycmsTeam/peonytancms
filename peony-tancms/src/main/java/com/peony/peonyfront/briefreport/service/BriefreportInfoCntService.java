package com.peony.peonyfront.briefreport.service;

import com.peony.peonyfront.briefreport.model.BriefreportInfoCnt;

public interface BriefreportInfoCntService {
    /**
     * 根据briefreportInfoId查询文章详细
     * 
     * @param briefreportInfoId
     * @return
     */
    BriefreportInfoCnt selectByPrimaryKey(Integer briefreportInfoId);

    /**
     * 删除选中的简报信息
     * 
     * @param briefreportInfoIds
     * @return
     */
    int delBriefreportInfoCntByBriefreportInfoIds(String[] briefreportInfoIds);
}
