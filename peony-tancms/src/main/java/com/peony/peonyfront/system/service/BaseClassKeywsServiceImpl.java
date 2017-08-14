package com.peony.peonyfront.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.system.dao.BaseClassKeywsMapper;
import com.peony.peonyfront.system.model.BaseClassKeyws;

/**
 * 基础类型关键字接口实现
 * 
 * @author jhj
 *
 */
@Service
public class BaseClassKeywsServiceImpl implements BaseClassKeywsService {

    @Resource
    private BaseClassKeywsMapper baseClassKeywsMapper;

    @Override
    public List<BaseClassKeyws> selectBaseClassKeywsByBaseClassId(BaseClassKeyws baseClassKeyws) {
        return this.baseClassKeywsMapper.selectBaseClassKeywsByBaseClassId(baseClassKeyws);
    }

    @Override
    public List<BaseClassKeyws> selectBaseClassKeywsByBaseclassKeywsId(BaseClassKeyws baseClassKeyws) {
        return this.baseClassKeywsMapper.selectBaseClassKeywsByBaseclassKeywsId(baseClassKeyws);
    }

}
