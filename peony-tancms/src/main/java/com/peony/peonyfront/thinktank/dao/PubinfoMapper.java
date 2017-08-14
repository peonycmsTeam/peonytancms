package com.peony.peonyfront.thinktank.dao;

import java.util.List;

import com.peony.peonyfront.thinktank.model.Pubinfo;

public interface PubinfoMapper {
    int deleteByPrimaryKey(Integer pubinfoId);

    int insert(Pubinfo record);

    int insertSelective(Pubinfo record);

    Pubinfo selectByPrimaryKey(Integer pubinfoId);

    int updateByPrimaryKeySelective(Pubinfo record);

    int updateByPrimaryKey(Pubinfo record);

    /**
     * 案例库
     * 
     * @param pubinfo
     * @return
     */
    List<Pubinfo> selectPubinfoByPage(Pubinfo pubinfo);

    /**
     * 处置经验
     * 
     * @param pubinfo
     * @return
     */
    List<Pubinfo> selectPubByPage(Pubinfo pubinfo);

    /**
     * 点击标题，查看详细
     * 
     * @param pubinfoId
     * @return
     */
    Pubinfo selectContentByPrimaryKey(Pubinfo pubinfo);

    /**
     * 条件查询 不分页
     * 
     * @param pubinfo
     * @return
     */
    List<Pubinfo> selectPubinfos(Pubinfo pubinfo);
}