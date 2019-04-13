package com.ky.gps.entity;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author Daye
 */
@Component
public class Department extends AbstractEntity {

    private Integer id;
    private String name;
    private String code;
    private String upLevel;
    private Integer parentId;
    private Byte leaf;

    /** 无参构造方法 */
    public Department() {
    }

    /** 有参构造方法 */
    public Department(String remark, String remark1,
                      String remark2, String remark3,
                      Timestamp createdDate, String createdBy,
                      Timestamp updatedDate, String updatedBy,
                      Boolean valid, Integer id,
                      String name, String code,
                      String upLevel, Integer parentId,
                      Byte leaf) {
        super(remark, remark1,
                remark2, remark3,
                createdDate, createdBy,
                updatedDate, updatedBy,
                valid);
        this.id = id;
        this.name = name;
        this.code = code;
        this.upLevel = upLevel;
        this.parentId = parentId;
        this.leaf = leaf;
    }

    /**
     * 重写toString方法
     * @return 返回属性值
     */
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", upLevel='" + upLevel + '\'' +
                ", parentId=" + parentId +
                ", leaf=" + leaf +
                '}' +
                super.toString();
    }

    /** getter/setter */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpLevel() {
        return upLevel;
    }

    public void setUpLevel(String upLevel) {
        this.upLevel = upLevel;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Byte getLeaf() {
        return leaf;
    }

    public void setLeaf(Byte leaf) {
        this.leaf = leaf;
    }
}
