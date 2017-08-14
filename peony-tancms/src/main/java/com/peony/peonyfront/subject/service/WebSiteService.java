package com.peony.peonyfront.subject.service;

import java.util.List;

import com.peony.peonyfront.subject.model.WebSite;

/**
 * 网站信息服务
 * 
 * @author vv
 *
 */
public interface WebSiteService {

    public List<WebSite> findWebSiteByUrl(WebSite webSite);
}
