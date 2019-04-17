package com.ky.gps.entity;

import java.sql.Timestamp;

/**
 * @author Daye
 * 用户操作记录
 */
public class SysLog extends AbstractEntity {

    /**
     * 记录id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer sysUserId;
    /**
     * 用户的职工编号
     */
    private String workId;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 所在部门名
     */
    private String departmentName;
    /**
     * 操作
     */
    private String operate;
    /**
     * 模块
     */
    private String module;
    /**
     * ip地址
     */
    private String ipAddress;
    /**
     * 物理地址
     */
    private String macAddress;
    /**
     * 内容
     */
    private String content;

    /**
     * 无参构造函数
     */
    public SysLog() {
    }

    /**
     * 有参构造函数-自身属性
     */
    public SysLog(Integer id, Integer sysUserId,
                  String workId, String realName,
                  String departmentName, String operate,
                  String module, String ipAddress,
                  String macAddress, String content) {
        this.id = id;
        this.sysUserId = sysUserId;
        this.workId = workId;
        this.realName = realName;
        this.departmentName = departmentName;
        this.operate = operate;
        this.module = module;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.content = content;
    }

    /**
     * 有参构造函数-所有属性
     */
    public SysLog(String remark, String remark1,
                  String remark2, String remark3,
                  Timestamp createdDate, String createdBy,
                  Timestamp updatedDate, String updatedBy,
                  Boolean valid, Integer id, Integer sysUserId,
                  String workId, String realName,
                  String departmentName, String operate,
                  String module, String ipAddress,
                  String macAddress, String content) {
        super(remark, remark1, remark2, remark3, createdDate, createdBy, updatedDate, updatedBy, valid);
        this.id = id;
        this.sysUserId = sysUserId;
        this.workId = workId;
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
                "id=" + id +
                ", sysUserId=" + sysUserId +
                ", workId='" + workId + '\'' +
                ", realName='" + realName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", operate='" + operate + '\'' +
                ", module='" + module + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    /**
     * getter/setter
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
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
