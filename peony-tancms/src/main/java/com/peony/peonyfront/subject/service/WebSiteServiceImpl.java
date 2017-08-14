package com.peony.peonyfront.subject.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.subject.dao.WebSiteMapper;
import com.peony.peonyfront.subject.model.WebSite;

@Service
public class WebSiteServiceImpl implements WebSiteService {

    @Resource
    private WebSiteMapper webSiteMapper;

    @Override
    public List<WebSite> findWebSiteByUrl(WebSite webSite) {
        return this.webSiteMapper.selectByUrl(webSite);
    }

}
