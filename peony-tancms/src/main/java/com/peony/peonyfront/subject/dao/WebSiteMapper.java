package com.peony.peonyfront.subject.dao;

import java.util.List;

import com.peony.peonyfront.subject.model.WebSite;

public interface WebSiteMapper {

    /**
     * 通过URL查询网站信息
     * 
     * @param webSite
     * @return
     */
    List<WebSite> selectByUrl(WebSite webSite);

}