package com.gps.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Daye
 * 系统用户
 * Name	Code	Data Type
 * 职工编号	WORKID	Variable characters (200)
 * 姓名	REAL_NAME	Variable characters (100)
 * 密码	PASSWORD	Variable characters (100)
 * SALT	SALT	Variable characters (100)
 * 身份证号码	ID_CODE	Variable characters (100)
 * 联系电话	PHONE	Variable characters (200)
 * 电子邮件	EMAIL	Variable characters (200)
 * 开通日期	ACOUNT_DATE	Date
 * 上一次密码修改日期	LAST_PSD_DATE	Date
 */
@Component
@Scope(value = "singleton")
public class SysUser extends AbstractEntity {

    /** ID */
    private int id;
    /** 职工编号 */
    private String workId;
    /** 姓名 */
    private String realName;
    /** 密码 */
    private String password;
    /** 加密密码 */
    private String salt;
    /** 身份证号码 */
    private String idCard;
    /** 联系电话 */
    private String phone;
    /** 电子邮件 */
    private String email;
    /** 上一次密码修改日期 */
    private Date lastPsdDate;


    /** 构造方法 */
    public SysUser() {
    }

    public SysUser(String remark, String remark1,
                   String remark2, String remark3,
                   Timestamp createdDate, String createBy,
                   Timestamp updateDate, String updateBy,
                   boolean valid, int id,
                   String workId, String realName,
                   String password, String salt,
                   String idCard, String phone,
                   String email, Date lastPsdDate) {
        super(remark, remark1, remark2, remark3, createdDate, createBy, updateDate, updateBy, valid);
        this.id = id;
        this.workId = workId;
        this.realName = realName;
        this.password = password;
        this.salt = salt;
        this.idCard = idCard;
        this.phone = phone;
        this.email = email;
        this.lastPsdDate = lastPsdDate;
    }

    /**
     * 重写toString
     * @return 返回实体属性
     */
    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", workId='" + workId + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", idCard='" + idCard + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", lastPsdDate=" + lastPsdDate +
                "}\n" +
                super.toString();
    }

    /** getter/setter start */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastPsdDate() {
        return lastPsdDate;
    }

    public void setLastPsdDate(Date lastPsdDate) {
        this.lastPsdDate = lastPsdDate;
    }
}
