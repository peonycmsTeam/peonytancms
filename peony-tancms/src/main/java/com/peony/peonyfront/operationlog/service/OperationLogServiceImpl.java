package com.peony.peonyfront.operationlog.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.operationlog.dao.OperationLogMapper;
import com.peony.peonyfront.operationlog.model.OperationLog;

/**
 * 操作日志
 * 
 * @author jhj
 * @version 1
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Resource
    private OperationLogMapper operationLogMapper;

    @Override
    public int insertSelective(OperationLog record) {
        String key = UUID.randomUUID().toString();
        record.setOperationLogId(key);
        return this.operationLogMapper.insertSelective(record);
    }

    @Override
    public List<OperationLog> selectTop20(OperationLog operationLog) {
        return this.operationLogMapper.selectTop20(operationLog);
    }

}
