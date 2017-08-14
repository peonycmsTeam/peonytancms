package com.peony.peonyfront.thinktank.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.thinktank.dao.PubinfoMapper;
import com.peony.peonyfront.thinktank.model.Pubinfo;

@Service
public class PubinfoServiceImpl implements PubinfoService {
    @Resource
    private PubinfoMapper pubinfoMapper;

    @Override
    public Pagination<Pubinfo> selectPubinfoByPage(Pubinfo pubinfo) {
        List<Pubinfo> pubinfoes = this.pubinfoMapper.selectPubinfoByPage(pubinfo);
        return new Pagination<Pubinfo>(pubinfoes, pubinfo.getPageParameter());
    }

    @Override
    public Pubinfo selectContentByPrimaryKey(Pubinfo pubinfo) {
        return this.pubinfoMapper.selectContentByPrimaryKey(pubinfo);
    }

    @Override
    public Pagination<Pubinfo> selectPubinfos(Pubinfo pubinfo) {
        List<Pubinfo> list = this.pubinfoMapper.selectPubinfos(pubinfo);
        return new Pagination<Pubinfo>(list, pubinfo.getPageParameter());
    }

    @Override
    public Pagination<Pubinfo> selectPubByPage(Pubinfo pubinfo) {
        List<Pubinfo> list = this.pubinfoMapper.selectPubByPage(pubinfo);
        return new Pagination<Pubinfo>(list, pubinfo.getPageParameter());
    }

}
