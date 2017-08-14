package com.peony.peonyfront.focus.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.focus.dao.FocusPageMapper;
import com.peony.peonyfront.focus.model.FocusPage;

@Service
public class FocusPageServiceImpl implements FocusPageService {
    @Resource
    private FocusPageMapper focusPageMapper;

    @Override
    public FocusPage selectByPrimaryKey(String id) {
        return this.focusPageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(FocusPage record) {
        return this.focusPageMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return this.focusPageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(FocusPage record) {
        return this.focusPageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertSelective(FocusPage record) {
        return this.focusPageMapper.insert(record);
    }

    @Override
    public Pagination<FocusPage> selectByPage(FocusPage record) {
        List<FocusPage> topics = this.focusPageMapper.selectByPage(record);

        return new Pagination<FocusPage>(topics, record.getPageParameter());
    }

    @Override
    public List selectByCount(Map map) {
        List countList = this.focusPageMapper.selectByCount(map);
        return countList;
    }

    @Override
    public List selectByMedia(Map map) {
        List countList = this.focusPageMapper.selectByMedia(map);
        return countList;
    }

    @Override
    public List selectByMediaCount(Map map) {
        List countList = this.focusPageMapper.selectByMediaCount(map);
        return countList;
    }

    @Override
    public List selectByTypeCount(Map map) {
        List countList = this.focusPageMapper.selectByTypeCount(map);
        return countList;
    }

    @Override
    public List<FocusPage> selectFocusPageByIds(String[] ids) {
        return this.focusPageMapper.selectFocusPageByIds(ids);
    }

    @Override
    public List<FocusPage> selectFocusBySelectTime(FocusPage record) {
        return this.focusPageMapper.selectFocusBySelectTime(record);
    }

}
