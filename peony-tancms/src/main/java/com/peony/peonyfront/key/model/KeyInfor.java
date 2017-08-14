package com.peony.peonyfront.key.model;

import java.io.Serializable;

import com.peony.peonyfront.key.dao.IdMapper;

public class KeyInfor implements Serializable {

    private static final long serialVersionUID = 1L;
    IdMapper                  idMapper;
    private int               keyMax;
    private int               keyMin;
    private int               keyNext;
    private int               poolSize;
    private String            keyName;
    private Id                id               = new Id();

    public KeyInfor(int i, String s, IdMapper idMapper) {
        this.idMapper = idMapper;
        poolSize = i;
        keyName = s;
        retrieveFromDB();
    }

    public int getKeyMax() {
        return keyMax;
    }

    public int getKeyMin() {
        return keyMin;
    }

    public int getNextKey() {
        if (keyNext > keyMax)
            retrieveFromDB();
        return keyNext++;
    }

    private void retrieveFromDB() {
        id.setIdValue(poolSize);
        id.setIdKey(keyName);
        int i = idMapper.updateByPrimaryKeySelective(id);
        int j = 0;
        if (i == -1) {

            return;
        }
        if (i == 1) {
            Id id = idMapper.selectByPrimaryKey(keyName);
            if (id != null) {

                j = id.getIdValue();
            }
        } else if (i == 0) {
            id.setIdKey(keyName);
            id.setIdValue(poolSize);
            idMapper.insert(id);
            j = poolSize;
        } else {

            return;
        }
        keyMax = j;
        keyMin = (j - poolSize) + 1;
        keyNext = keyMin;
    }
}
