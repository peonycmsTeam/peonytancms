package com.peony.peonyfront.login.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

/**
 * user
 *
 * @author jhj
 */
public class User extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           userId;

    private Integer           agentId;

    private String            loginName;

    private String            password;

    private String            deptId;

    private String            name;

    private String            email;

    private String            mobile;

    private String            sex;

    private String            company;

    private String            address;

    private String            note;

    private Date              createTime;

    private String            createUser;

    private Date              lastLoginTime;

    private String            useAgentLogo;         // 1 使用 0不使用

    private String            userType;             // 1.企业 2.政府

    private String            platformType;         // 1.正式 2.试用 3.演示
    private Date              expiryTime;

    private String            startTime;            // 查询开始时间
    private String            endTime;              // 查询结束时间

    private String            userStatus;
    private String            orderFields;
    private String            order;

    private String            roleId;               // 查询分页使用

    private String            personType;           // 区分市场用户 和客户
    private String            isDelete;             // 查询分页使用

    private String            userSource;           // 0：标识直销 1：标识代理商'

    private Integer           belongId;             // 所管辖客户的市场人员id

    private String            userStyle;            // 用户样式

    public String getUserStyle() {
        return this.userStyle;
    }

    public void setUserStyle(String userStyle) {
        this.userStyle = userStyle;
    }

    private String userIds;// 所属的销售人员id

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getOrderFields() {
        return this.orderFields;
    }

    public String getUseAgentLogo() {
        return this.useAgentLogo;
    }

    public void setUseAgentLogo(String useAgentLogo) {
        this.useAgentLogo = useAgentLogo;
    }

    public void setOrderFields(String orderFields) {
        this.orderFields = orderFields;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getExpiryTime() {
        return this.expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getLastLoginTime() {
        return this.lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserStatus() {
        return this.userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPersonType() {
        return this.personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getAgentId() {
        return this.agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getUserSource() {
        return this.userSource;
    }

    public void setUserSource(String userSource) {
        this.userSource = userSource;
    }

    public Integer getBelongId() {
        return this.belongId;
    }

    public void setBelongId(Integer belongId) {
        this.belongId = belongId;
    }

    public String getUserIds() {
        return this.userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getPlatformType() {
        return this.platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public User(Integer userId, Integer agentId, String loginName, String password, String deptId, String name, String email, String mobile, String sex, String company, String address, String note, Date createTime, String createUser, Date lastLoginTime, String userType, String userStatus, String orderFields, String order, String personType, String isDelete, String userSource, Integer belongId, String platformType) {
        this.userId = userId;
        this.agentId = agentId;
        this.loginName = loginName;
        this.password = password;
        this.deptId = deptId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.sex = sex;
        this.company = company;
        this.address = address;
        this.note = note;
        this.createTime = createTime;
        this.createUser = createUser;
        this.lastLoginTime = lastLoginTime;
        this.userType = userType;
        this.userStatus = userStatus;
        this.orderFields = orderFields;
        this.order = order;
        this.personType = personType;
        this.isDelete = isDelete;
        this.userSource = userSource;
        this.belongId = belongId;
        this.platformType = platformType;
    }

    public User() {
    }
}