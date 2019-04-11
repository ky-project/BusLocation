package com.gps.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Daye
 * 教师实体类，单例
 */
@Component
public class Teacher implements Serializable {
    /** 教师id **/
    private Long teacherId;
    /** 教工号 **/
    private String jobNumber;
    /** 密码 **/
    private String password;
    /** 教师姓名 **/
    private String teaName;
    /** 所在系部 **/
    private String department;
    /** 性别-0:女，1：男 **/
    private Integer gender;
    /** 电话号码 **/
    private String telPhone;
    /** 手机号码 **/
    private String mobile;
    /** 邮箱 **/
    private String email;
    /** 授权码 **/
    private String idToken;

    /** 无参构造函数 **/
    public Teacher() {
    }

    /** 有参构造函数 **/
    public Teacher(Long teacherId, String jobNumber,
                   String teaName, String department,
                   Integer gender, String telPhone,
                   String mobile, String email,
                   String idToken) {
        this.teacherId = teacherId;
        this.jobNumber = jobNumber;
        this.teaName = teaName;
        this.department = department;
        this.gender = gender;
        this.telPhone = telPhone;
        this.mobile = mobile;
        this.email = email;
        this.idToken = idToken;
    }

    /**
     * getter/ setter
     */
    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    /**
     * 重写toString
     * @return 属性集合
     */
    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", jobNumber='" + jobNumber + '\'' +
                ", password='" + password + '\'' +
                ", teaName='" + teaName + '\'' +
                ", department='" + department + '\'' +
                ", gender=" + gender +
                ", telPhone='" + telPhone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", idToken='" + idToken + '\'' +
                '}';
    }
}
