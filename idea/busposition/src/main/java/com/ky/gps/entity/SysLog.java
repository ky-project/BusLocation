package com.ky.gps.entity;

/**
 * @author Daye
 * 用户操作记录
 */
public class SysLog {

    /** 用户id */
    private Integer sysUserId;
    /** 用户的登录名，用户账号/职工编号 */
    private String userName;
    /** 真实姓名 */
    private String realName;
    /** 所在部门名 */
    private String departmentName;
    /** 操作 */
    private String operate;
    /** 模块 */
    private String module;
    /** ip地址 */
    private String ipAddress;
    /** 物理地址 */
    private String macAddress;
    /** 内容 */
    private String content;

    /** 无参构造函数 */
    public SysLog() {
    }

    /** 有参构造函数 */
    public SysLog(Integer sysUserId, String userName,
                  String realName, String departmentName,
                  String operate, String module,
                  String ipAddress, String macAddress,
                  String content) {
        this.sysUserId = sysUserId;
        this.userName = userName;
        this.realName = realName;
        this.departmentName = departmentName;
        this.operate = operate;
        this.module = module;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.content = content;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "sysUserId=" + sysUserId +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", operate='" + operate + '\'' +
                ", module='" + module + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    /** getter/setter */
    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
