package com.peony.peonyfront.attention.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.attention.dao.AttentionInfoMapper;
import com.peony.peonyfront.attention.dao.AttentionMapper;
import com.peony.peonyfront.attention.model.Attention;
import com.peony.peonyfront.util.log.Action;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

@Service
public class AttentionServiceImpl implements AttentionService {

    @Resource
    private AttentionMapper     attentionMapper;
    @Resource
    private AttentionInfoMapper attentionInfoMapper;

    @Override
    public Pagination<Attention> findByPage(Attention attention) {
        List<Attention> attentions = this.attentionMapper.selectByPage(attention);
        return new Pagination<Attention>(attentions, attention.getPageParameter());
    }

    @Override
    public Attention selectByPrimaryKey(Integer attentionId) {

        return this.attentionMapper.selectByPrimaryKey(attentionId);
    }

    @Override
    public List<Attention> findAll(Attention attention) {
        return this.attentionMapper.selectAll(attention);
    }

    @Override
    @Action(description = "删除收藏夹", operateMode = OperateMode.我的收藏, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.DELETE)
    public int deleteByPrimaryKey(Integer attentionId) {
        this.attentionInfoMapper.delAttentionInfoByAttentionId(attentionId);
        return this.attentionMapper.deleteByPrimaryKey(attentionId);
    }

    @Override
    @Action(description = "修改收藏夹", operateMode = OperateMode.我的收藏, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.UPDATE)
    public int updateByPrimaryKeySelective(Attention record) {
        return this.attentionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Action(description = "新增收藏夹", operateMode = OperateMode.我的收藏, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.SAVE)
    public int insertSelective(Attention record) {
        return this.attentionMapper.insertSelective(record);
    }

}
