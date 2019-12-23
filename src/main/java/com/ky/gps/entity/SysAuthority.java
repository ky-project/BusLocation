package com.ky.gps.entity;

import java.sql.Timestamp;

/**
 * 权限表实体类
 *
 * @author Daye
 */
public class SysAuthority extends AbstractEntity {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 权限组
     */
    private String saGroup;

    /**
     * 权限名
     */
    private String saName;

    /**
     * 权限显示名
     */
    private String saDisplayName;

    /**
     * 构造方法-无参
     */
    public SysAuthority() {
    }

    /**
     * 构造方法-自身属性
     */
    public SysAuthority(Integer id, String saGroup,
                        String saName, String saDisplayName) {
        this.id = id;
        this.saGroup = saGroup;
        this.saName = saName;
        this.saDisplayName = saDisplayName;
    }

    /**
     * 构造方法-所有参数
     */
    public SysAuthority(String remark, String remark1,
                        String remark2, String remark3,
                        Timestamp createdDate, String createdBy,
                        Timestamp updatedDate, String updatedBy,
                        Boolean valid, Integer id, String saGroup,
                        String saName, String saDisplayName) {
        super(remark, remark1, remark2, remark3, createdDate, createdBy, updatedDate, updatedBy, valid);
        this.id = id;
        this.saGroup = saGroup;
        this.saName = saName;
        this.saDisplayName = saDisplayName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaGroup() {
        return saGroup;
    }

    public void setSaGroup(String saGroup) {
        this.saGroup = saGroup;
    }

    public String getSaName() {
        return saName;
    }

    public void setSaName(String saName) {
        this.saName = saName;
    }

    public String getSaDisplayName() {
        return saDisplayName;
    }

    public void setSaDisplayName(String saDisplayName) {
        this.saDisplayName = saDisplayName;
    }

    @Override
    public String toString() {
        return "SysAuthority{" +
                "id=" + id +
                ", saGroup='" + saGroup + '\'' +
                ", saName='" + saName + '\'' +
                ", saDisplayName='" + saDisplayName + '\'' +
                '}';
    }
}