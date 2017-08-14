package com.peony.peonyfront.subject.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.subject.dao.WebDictionaryMapper;
import com.peony.peonyfront.subject.model.WebDictionary;

@Service
public class WebDictionaryServiceImpl implements WebDictionaryService {

    @Resource
    private WebDictionaryMapper WebDictionaryMapper;

    @Override
    public List<WebDictionary> findAllWebDictionary() {
        return this.WebDictionaryMapper.selectWebDictionary();
    }

}
