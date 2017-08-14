package com.peony.peonyfront.key.model;

import java.io.Serializable;
import java.util.HashMap;

import com.peony.peonyfront.key.dao.IdMapper;

public class KeyGen implements Serializable {

    private static final long serialVersionUID = 8935641270717729429L;
    private static KeyGen     keyGen           = new KeyGen();
    private static int        POOLSIZE         = 1;
    private HashMap           keyList          = new HashMap(50);;

    public static KeyGen getInstance() {
        return keyGen;
    }

    public synchronized int getNextKey(String s, IdMapper idMapper) {
        KeyInfor keyinfor;
        if (keyList.containsKey(s)) {
            keyinfor = (KeyInfor) keyList.get(s);
        } else {
            keyinfor = new KeyInfor(POOLSIZE, s, idMapper);
            keyList.put(s, keyinfor);
        }
        return keyinfor.getNextKey();
    }

    public static void main(String[] args) {
        // System.out.println(new KeyGen().getInstance().getNextKey("UserID"));
        // System.out.println(new KeyGen().getInstance().getNextKey("UserID"));
    }
}
