package com.peony.peonyfront.subjectkeywords.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.subjectkeywords.dao.SubjectKeywordsMapper;
import com.peony.peonyfront.subjectkeywords.model.SubjectKeywords;
import com.peony.peonyfront.util.log.Action;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

@Service
public class SubjectKeywordsServiceImpl implements SubjectKeywordsService {
    @Resource
    private SubjectKeywordsMapper subjectKeywordsMapper;

    @Override
    public List<SubjectKeywords> selectSubjectListBySubjectId(Integer subjectId) {
        return this.subjectKeywordsMapper.selectSubjectListBySubjectId(subjectId);
    }

    @Override
    @Action(description = "删除定制舆情关键字", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.DELETE)
    public int deleteBySubjectId(Integer subjectId) {
        return this.subjectKeywordsMapper.deleteBySubjectId(subjectId);
    }

    @Override
    @Action(description = "新增定制舆情关键字", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.SAVE)
    public int insertSelective(SubjectKeywords record) {
        return this.subjectKeywordsMapper.insertSelective(record);
    }

    @Override
    public List<SubjectKeywords> selectSubjectListByUserId(Integer userId) {
        return this.subjectKeywordsMapper.selectSubjectListByUserId(userId);
    }

}
