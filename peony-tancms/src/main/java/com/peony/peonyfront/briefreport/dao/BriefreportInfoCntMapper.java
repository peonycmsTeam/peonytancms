package com.peony.peonyfront.briefreport.dao;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.briefreport.model.BriefreportInfoCnt;

/**
 * ������DAO
 * 
 * @author yanglin
 *
 */
public interface BriefreportInfoCntMapper {

    /**
     * ͨ������ɾ�������
     * 
     * @param briefreportInfoId
     * @return
     */
    int deleteByPrimaryKey(Integer briefreportInfoId);

    /**
     * ���������ݣ��������ж�
     * 
     * @param record
     * @return
     */
    int insert(BriefreportInfoCnt briefreportInfoCnt);

    /**
     * ���������ݣ��������ж�
     * 
     * @param record
     * @return
     */
    int insertSelective(BriefreportInfoCnt briefreportInfoCnt);

    /**
     * ͨ�������ѯ������
     * 
     * @param briefreportInfoId
     * @return
     */
    BriefreportInfoCnt selectByPrimaryKey(Integer briefreportInfoId);

    /**
     * �������жϸ��¼�����
     * 
     * @param briefreportInfoCnt
     * @return
     */
    int updateByPrimaryKeySelective(BriefreportInfoCnt briefreportInfoCnt);

    /**
     * �������жϸ��¼�����
     * 
     * @param briefreportInfoCnt
     * @return
     */
    int updateByPrimaryKey(BriefreportInfoCnt briefreportInfoCnt);

    /**
     * 删除选中的简报信息
     * 
     * @param briefreportInfoIds
     * @return
     */
    int delBriefreportInfoCntByBriefreportInfoIds(@Param(value = "briefreportInfoIds") String[] briefreportInfoIds);
}