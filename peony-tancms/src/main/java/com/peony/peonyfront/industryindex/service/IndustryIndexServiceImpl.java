package com.peony.peonyfront.industryindex.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.industryindex.dao.IndustryIndexMapper;
import com.peony.peonyfront.industryindex.model.IndustryIndex;

@Service
public class IndustryIndexServiceImpl implements IndustryIndexService {

    @Resource
    private IndustryIndexMapper industryIndexMapper;

    @Override
    public int insert(IndustryIndex industryIndex) {
        return this.industryIndexMapper.insertSelective(industryIndex);
    }

    @Override
    public List<IndustryIndex> selectIndustryIndexByDate(String time) {
        return this.industryIndexMapper.selectIndustryIndexByDate(time);
    }

    @Override
    public List<IndustryIndex> selectTdistribteSheng(IndustryIndex industryindex) {
        // TODO Auto-generated method stub
        return this.industryIndexMapper.selectTdistribteSheng(industryindex);
    }

    @Override
    public List<IndustryIndex> countSelectByCount(IndustryIndex industryindex) {
        // TODO Auto-generated method stub
        return this.industryIndexMapper.selectIndustryIndex(industryindex);
    }

    @Override
    public List<IndustryIndex> distributeSelectByCount(IndustryIndex industryindex) {
        // TODO Auto-generated method stub
        return this.industryIndexMapper.selectdistribute(industryindex);
    }

    @Override
    public List<IndustryIndex> IndustryIndexSelectByCount(IndustryIndex industryindex) {
        // TODO Auto-generated method stub
        return this.industryIndexMapper.selectIndustryCount(industryindex);

    }

    @Override
    public List<IndustryIndex> countSelectByCountZhou(IndustryIndex industryindex) {
        // TODO Auto-generated method stub
        return this.industryIndexMapper.selectIndustryIndexZhou(industryindex);
    }

    @Override
    public List<IndustryIndex> countSelectByCountYue(IndustryIndex industryindex) {
        // TODO Auto-generated method stub
        return this.industryIndexMapper.selectIndustryIndexYue(industryindex);
    }

    @Override
    public List<IndustryIndex> selectdistributeZhou(IndustryIndex industryindex) {
        // TODO Auto-generated method stub
        return this.industryIndexMapper.selectdistributeZhou(industryindex);
    }

    @Override
    public List<IndustryIndex> selectdistributeYue(IndustryIndex industryindex) {
        // TODO Auto-generated method stub
        return this.industryIndexMapper.selectdistributeYue(industryindex);
    }

    @Override
    public List<IndustryIndex> selectIndustryCountZhou(IndustryIndex industryindex) {
        // TODO Auto-generated method stub
        return this.industryIndexMapper.selectIndustryCountZhou(industryindex);
    }

    @Override
    public List<IndustryIndex> selectIndustryCountYue(IndustryIndex industryindex) {
        // TODO Auto-generated method stub
        return this.industryIndexMapper.selectIndustryCountYue(industryindex);
    }

    @Override
    public List<IndustryIndex> selectTdistribteShengZhou(IndustryIndex industryindex) {
        // TODO Auto-generated method stub
        return this.industryIndexMapper.selectTdistribteShengZhou(industryindex);
    }

    @Override
    public List<IndustryIndex> selectTdistribteShengYue(IndustryIndex industryindex) {
        // TODO Auto-generated method stub
        return this.industryIndexMapper.selectTdistribteShengYue(industryindex);
    }
}
