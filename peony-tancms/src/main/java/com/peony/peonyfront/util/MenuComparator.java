package com.peony.peonyfront.util;

import java.util.Comparator;

import com.peony.peonyfront.login.model.Menu;

/**
 * menu 比较器 通过code对菜单排序
 * 
 * @author jhj
 *
 */
public class MenuComparator implements Comparator<Menu> {
    @Override
    public int compare(Menu o1, Menu o2) {
        return o1.getIdentify().compareTo(o2.getIdentify());
    }

}
