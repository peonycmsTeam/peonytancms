package com.peony.peonyfront.operationlog.service;

import java.util.List;

import com.peony.peonyfront.operationlog.model.OperationLog;

public interface OperationLogService {

    /**
     * 插入选择数据
     * 
     * @param record
     * @return
     */
    int insertSelective(OperationLog record);

    List<OperationLog> selectTop20(OperationLog operationLog);
}
