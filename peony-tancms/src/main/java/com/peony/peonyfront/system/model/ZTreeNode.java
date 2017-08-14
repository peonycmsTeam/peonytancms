package com.peony.peonyfront.system.model;

public class ZTreeNode implements java.io.Serializable {

    private static final long serialVersionUID = -8969147760938959419L;
    private String            id;
    private String            name;
    private boolean           open;                                    // 初始化展开还是关闭
    private String            isParent;                                // 是：则代表有子节点,Ztree会显示+，并点击会再加载子节点
    private String            iconSkin;
    private String            pId;

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }
}
