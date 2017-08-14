package com.peony.peonyfront.thinktank.service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.thinktank.model.Pubinfo;

public interface PubinfoService {
    /**
     * 案例库分页
     * 
     * @param pubinfo
     * @return
     */
    Pagination<Pubinfo> selectPubinfoByPage(Pubinfo pubinfo);

    /**
     * 处置经验
     * 
     * @param pubinfo
     * @return
     */
    Pagination<Pubinfo> selectPubByPage(Pubinfo pubinfo);

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
    Pagination<Pubinfo> selectPubinfos(Pubinfo pubinfo);
}
