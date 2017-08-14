package com.peony.peonyfront.subject.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.subject.dao.SubjectMapper;
import com.peony.peonyfront.subject.model.Subject;
import com.peony.peonyfront.util.log.Action;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

/**
 * 订制舆情实现类
 * 
 * @author vv
 *
 */
@Service
public class SubjectServiceImpl implements SubjectService {
    @Resource
    private SubjectMapper subjectMapper;

    @Override
    public List<Subject> selectUserKeywsByUserId(Integer userId) {
        return this.subjectMapper.selectSubjectByUserId(userId);
    }

    @Override
    public Subject selectByPrimaryKey(Integer id) {
        return this.subjectMapper.selectByPrimaryKey(id);
    }

    @Override
    @Action(description = "修改定制舆情分类", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.UPDATE)
    public int updateByPrimaryKey(Subject record) {
        return this.subjectMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Subject> selectChildnodesById(Integer id) {
        return this.subjectMapper.selectChildnodesById(id);
    }

    @Override
    @Action(description = "删除定制舆情分类", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.DELETE)
    public int deleteByPrimaryKey(Integer id) {
        return this.subjectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Subject> selectNodesByUserId(Integer userId) {
        return this.subjectMapper.selectNodesByUserId(userId);
    }

    @Override
    @Action(description = "修改定制舆情分类", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.UPDATE)
    public int updateByPrimaryKeySelective(Subject record) {
        return this.subjectMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Action(description = "新增定制舆情分类", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.SAVE)
    public int insertSelective(Subject record) {
        return this.subjectMapper.insert(record);
    }

    @Override
    public List<Subject> selectSubjectByUserIdAndPid(Subject subject) {
        return this.subjectMapper.selectSubjectByUserIdAndPid(subject);
    }

    @Override
    public boolean selectCountByPid(Subject subject) {
        subject = this.subjectMapper.selectCountByPid(subject);
        return subject.getCount() > 0;
    }

    @Override
    public List<Subject> selectSubjectByUserIdExclusive(Subject subject) {
        return this.subjectMapper.selectSubjectByUserIdExclusive(subject);
    }

}
