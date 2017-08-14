package com.peony.peonyfront.subject.service;

import java.util.List;

import com.peony.peonyfront.subject.model.WebDictionary;

/**
 * 信息来源字典表
 * 
 * @author vv
 *
 */
public interface WebDictionaryService {

    public List<WebDictionary> findAllWebDictionary();
}
