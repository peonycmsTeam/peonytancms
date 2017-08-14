package com.peony.peonyfront.subject.model;

public class ZTreeNode implements java.io.Serializable {

    private static final long serialVersionUID = -8969147760938959419L;
    private String            id;

    private String            pId;

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private String  name;
    private boolean open;    // 初始化展开还是关闭
    private String  isParent;// 是：则代表有子节点,Ztree会显示+，并点击会再加载子节点
    private boolean hasUser; // 该部门是否有用户
    private String  iconSkin;

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

    public boolean isHasUser() {
        return hasUser;
    }

    public void setHasUser(boolean hasUser) {
        this.hasUser = hasUser;
    }
}
