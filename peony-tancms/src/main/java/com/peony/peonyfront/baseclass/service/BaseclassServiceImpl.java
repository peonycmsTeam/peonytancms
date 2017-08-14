package com.peony.peonyfront.baseclass.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.baseclass.dao.BaseclassMapper;
import com.peony.peonyfront.baseclass.model.Baseclass;

/**
 * 基础类别表
 *
 * @author zhyz
 *
 */
@Service
public class BaseclassServiceImpl implements BaseclassService {
    @Resource
    private BaseclassMapper baseclassMapper;

    /**
     * 查询所有父节点
     */
    @Override
    public List<Baseclass> selectBaseClassParent(String[] code) {
        return this.baseclassMapper.selectParent(code);
    }

    /**
     * 根据父节点查询子节点
     */
    @Override
    public List<Baseclass> selectBaseClassChildren(Integer parentId) {
        Baseclass baseclass = new Baseclass();
        baseclass.setBaseclassPid(parentId);
        return this.baseclassMapper.selectChildren(baseclass);
    }

    /**
     * 根据id查询该条信息
     */
    @Override
    public Baseclass selectBaseClassByBaseClassId(Integer id) {

        return this.baseclassMapper.selectByPrimaryKey(id);
    }

}
