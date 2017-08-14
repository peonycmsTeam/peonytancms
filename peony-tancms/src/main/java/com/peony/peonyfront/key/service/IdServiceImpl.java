package com.peony.peonyfront.key.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.key.dao.IdMapper;
import com.peony.peonyfront.key.model.KeyGen;

@Service
public class IdServiceImpl implements IdService {
    @Resource
    private IdMapper idMapper; // id mapper

    @Override
    public int NextKey(String keyname) {
        int key = KeyGen.getInstance().getNextKey(keyname, this.idMapper);
        return key;
    }

}
