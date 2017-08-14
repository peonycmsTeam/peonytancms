package com.peony.peonyfront.subject.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.peony.peonyfront.util.SubjectPageUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.subject.dao.FeedBackMapper;
import com.peony.peonyfront.subject.dao.SubjectPageMapper;
import com.peony.peonyfront.subject.model.FeedBack;
import com.peony.peonyfront.subject.model.SubjectPage;
import com.peony.peonyfront.util.log.Action;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

@Service
public class SubjectPageServiceImpl implements SubjectPageService {

    private final Log         logger = LogFactory.getLog(SubjectPageServiceImpl.class);

    @Resource
    private SubjectPageMapper subjectPageMapper;

    @Resource
    private FeedBackMapper    feedBackMapper;

    @Resource
    private IdService         idService;                                               // id服务接口

    @Override
    public Pagination<SubjectPage> findByPage(SubjectPage subjectPage) {
        Pagination<SubjectPage> pagination = new Pagination<SubjectPage>();
        List<SubjectPage> subjectPages = this.subjectPageMapper.selectByPage(subjectPage);

		// 去除重复条目相关
        int sizeBefore = subjectPages.size();
        subjectPages = SubjectPageUtil.removeDuplicateByTitle(subjectPages);
        int sizeAfter = subjectPages.size();
		int dup = sizeBefore - sizeAfter;
		if (dup > 0)
			logger.info("去除重复条目数: " + dup);

        if (null != subjectPages) {
            pagination = new Pagination<SubjectPage>(subjectPages, subjectPage.getPageParameter());
        } else {
            this.logger.warn("Can't find any subject pages!");
        }
        logger.info("获取总页数：" + pagination.getTotalCount());
        return pagination;
    }

    @Override
    @Action(description = "修改倾向性", operateMode = OperateMode.定制舆情, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.UPDATE)
    public int update(SubjectPage subjectPage) {
        this.subjectPageMapper.update(subjectPage);
        List<SubjectPage> subjectPages = this.subjectPageMapper.selectByPage(subjectPage);

        FeedBack feedBack = new FeedBack();
        feedBack.setFeedbackId(this.idService.NextKey("feedbackId"));
        feedBack.setInfoId(subjectPages.get(0).getId());
        feedBack.setInfoTitle(subjectPages.get(0).getTitle());
        if (subjectPages.get(0).getPolarity() == 1) {
            feedBack.setOpinion("信息倾向性修改为正面");
        } else if (subjectPages.get(0).getPolarity() == -1) {
            feedBack.setOpinion("信息倾向性修改为负面");
        } else {
            feedBack.setOpinion("信息倾向性修改为争议");
        }

        feedBack.setUserId(1);
        feedBack.setUserName("测试用户");
        feedBack.setTime(new Date());
        return this.feedBackMapper.insertSelective(feedBack);
    }

    @Override
    public SubjectPage load(SubjectPage subjectPage) {
        return this.subjectPageMapper.load(subjectPage);
    }

    @Override
    public int updateSubjectPageIsRead(SubjectPage subjectPage) {
        return this.subjectPageMapper.update(subjectPage);
    }

    @Override
    @Action(description = "删除定制舆情信息", operateMode = OperateMode.定制舆情, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.DELETE)
    public int batchDelete(SubjectPage subjectPage) {
        return this.subjectPageMapper.batchDelete(subjectPage);
    }

    @Override
    public List<SubjectPage> selectSubjectPageByIds(String[] ids) {
        return this.subjectPageMapper.selectSubjectPageByIds(ids);
    }

    @Override
    public List<SubjectPage> selectSubjectPageByTime(SubjectPage subjectPage) {
        return this.subjectPageMapper.selectSubjectPageByTime(subjectPage);
    }

    @Override
    public List<SubjectPage> downExcelSelectSubjectPageByTime(SubjectPage subjectPage) {
        return this.subjectPageMapper.selectSubjectPageByTime(subjectPage);
    }

    @Override
    public List totalSelectCount(SubjectPage subjectPage) {
        return this.subjectPageMapper.selectTotalCount(subjectPage);
    }

    @Override
    public List countSelectByCount(Map map) {
        return this.subjectPageMapper.selectByCount(map);
    }

    @Override
    public List<SubjectPage> countSubjectTypeByUserId(SubjectPage subjectPage) {
        return this.subjectPageMapper.countSubjectTypeByUserId(subjectPage);
    }

    @Override
    public List<SubjectPage> countSubjectWebSiteByUserId(SubjectPage subjectPage) {
        return this.subjectPageMapper.countSubjectWebSiteByUserId(subjectPage);
    }

    @Override
    public List<SubjectPage> countSubjectPolarityByUserId(SubjectPage subjectPage) {
        return this.subjectPageMapper.countSubjectPolarityByUserId(subjectPage);
    }

    @Override
    public List<SubjectPage> countSubjectPolarityAndTypeByUserId(SubjectPage subjectPage) {
        return this.subjectPageMapper.countSubjectPolarityAndTypeByUserId(subjectPage);
    }

    @Override
    public List<SubjectPage> selectSubjectPageByGroupseedid(SubjectPage subjectPage) {
        return this.subjectPageMapper.selectSubjectPageByGroupseedid(subjectPage);
    }

    @Override
    public Pagination<SubjectPage> selectGroupByTitle(SubjectPage subjectPage) {
        List<SubjectPage> subjectPages = this.subjectPageMapper.selectGroupByTitle(subjectPage);
        return new Pagination<SubjectPage>(subjectPages, subjectPage.getPageParameter());
    }

    @Override
    public List<SubjectPage> downExcelSelectSubjectPageByTimeNotColspan(SubjectPage subjectPage) {
        return this.subjectPageMapper.selectSubjectPageByTimeNotColspan(subjectPage);
    }

    @Override
    public int downExcelSelectSubjectPageByTimeCount(SubjectPage subjectPage) {
        return this.subjectPageMapper.selectSubjectPageByTimeCount(subjectPage);
    }

    @Override
    public int downExcelSelectSubjectPageByTimeNotColspanCount(SubjectPage subjectPage) {
        return this.subjectPageMapper.selectSubjectPageByTimeNotColspanCount(subjectPage);
    }

    @Override
    public List<SubjectPage> selectSubjectPageByIdsNotColspan(String[] ids) {
        return this.subjectPageMapper.selectSubjectPageByIdsNotColspan(ids);
    }

}
