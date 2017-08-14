package com.peony.peonyfront.warningkeyws.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.util.log.Action;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;
import com.peony.peonyfront.warningkeyws.dao.WarningkeywsMapper;
import com.peony.peonyfront.warningkeyws.model.Warningkeyws;

/**
 * 关键词设置
 * 
 * @author zhyz
 *
 */
@Service
public class WarningkeywsServiceImpl implements WarningkeywsService {
    @Resource
    private WarningkeywsMapper warningkeywsMapper;

    @Override
    @Action(description = "新增预警关键词", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.SAVE)
    public int insertWarningkeyws(Warningkeyws warningkeyws) {
        return this.warningkeywsMapper.insertSelective(warningkeyws);
    }

    @Override
    public Pagination<Warningkeyws> findByPage(Warningkeyws warningkeyws) {
        List<Warningkeyws> warningkeywsList = this.warningkeywsMapper.selectByPage(warningkeyws);
        return new Pagination<Warningkeyws>(warningkeywsList, warningkeyws.getPageParameter());
    }

    @Override
    @Action(description = "删除预警关键词", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.DELETE)
    public int deleteByPrimaryKey(Integer warningkeywsId) {
        return this.warningkeywsMapper.deleteByPrimaryKey(warningkeywsId);
    }

    @Override
    public Warningkeyws selectByPrimaryKey(Integer warningkeywsId) {
        return this.warningkeywsMapper.selectByPrimaryKey(warningkeywsId);
    }

    @Override
    @Action(description = "修改预警关键词", operateMode = OperateMode.前台系统配置, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.UPDATE)
    public int updateByPrimaryKey(Warningkeyws warningkeyws) {
        return this.warningkeywsMapper.updateByPrimaryKey(warningkeyws);
    }

}
