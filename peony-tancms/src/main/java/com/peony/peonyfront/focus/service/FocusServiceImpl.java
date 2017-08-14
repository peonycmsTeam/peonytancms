package com.peony.peonyfront.focus.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.focus.dao.FocusMapper;
import com.peony.peonyfront.focus.model.Focus;

@Service
public class FocusServiceImpl implements FocusService {
    @Resource
    private FocusMapper focusMapper;

    @Override
    public Focus selectByPrimaryKey(String id) {
        return this.focusMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Focus record) {
        return this.focusMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return this.focusMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Focus record) {
        return this.focusMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertSelective(Focus record) {
        return this.focusMapper.insert(record);
    }

    @Override
    public Pagination<Focus> selectByPage(Focus record) {
        List<Focus> focuss = this.focusMapper.selectByPage(record);

        return new Pagination<Focus>(focuss, record.getPageParameter());
    }

}
