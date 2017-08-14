package com.peony.peonyfront.attention.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.attention.dao.AttentionInfoMapper;
import com.peony.peonyfront.attention.model.AttentionInfo;
import com.peony.peonyfront.util.log.Action;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

@Service
public class AttentionInfoServiceImpl implements AttentionInfoService {
    @Resource
    private AttentionInfoMapper AttentionInfoMapper;

    @Override
    public Pagination<AttentionInfo> selectAttentionInfoByAttentionIdByPage(AttentionInfo attentionInfo) {
        List<AttentionInfo> list = this.AttentionInfoMapper.selectAttentionInfoByAttentionIdByPage(attentionInfo);
        return new Pagination<AttentionInfo>(list, attentionInfo.getPageParameter());
    }

    @Override
    @Action(description = "删除收藏夹中信息", operateMode = OperateMode.我的收藏, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.DELETE)
    public int delAttentionInfoByAttentionInfoIds(String[] attentionInfoIds) {
        try {
            this.AttentionInfoMapper.delAttentionInfoByAttentionInfoIds(attentionInfoIds);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public AttentionInfo selectByPrimaryKey(Integer attentionInfoId) {
        return this.AttentionInfoMapper.selectByPrimaryKey(attentionInfoId);
    }

    @Override
    @Action(description = "加入收藏夹", operateMode = OperateMode.我的收藏, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.SAVE)
    public int insertSelective(AttentionInfo record) {
        return this.AttentionInfoMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(AttentionInfo record) {
        return this.AttentionInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<AttentionInfo> selectAttentionInfoByAttentionId(AttentionInfo record) {
        return this.AttentionInfoMapper.selectAttentionInfoByAttentionId(record);
    }

}
