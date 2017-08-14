package com.peony.peonyfront.system.dao;

import java.util.List;

import com.peony.peonyfront.system.model.BaseClassKeyws;

public interface BaseClassKeywsMapper {

    List<BaseClassKeyws> selectBaseClassKeywsByBaseClassId(BaseClassKeyws baseClassKeyws);

    List<BaseClassKeyws> selectBaseClassKeywsByBaseclassKeywsId(BaseClassKeyws baseClassKeyws);
}