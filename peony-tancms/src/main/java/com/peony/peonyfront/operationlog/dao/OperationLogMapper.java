package com.peony.peonyfront.operationlog.dao;

import java.util.List;

import com.peony.peonyfront.operationlog.model.OperationLog;

public interface OperationLogMapper {
    int insert(OperationLog record);

    int insertSelective(OperationLog record);

    List<OperationLog> selectTop20(OperationLog operationLog);

}