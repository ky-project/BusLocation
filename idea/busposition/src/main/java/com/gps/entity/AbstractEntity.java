package com.gps.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Daye
 * 实体类的抽象父类
 * 包含所有表中共有的属性
 */
public abstract class AbstractEntity implements Serializable {
    /** 备注 */
    private String remark;
    /** 备注1 */
    private String remark1;
    /** 备注2 */
    private String remark2;
    /** 备注3 */
    private String remark3;
    /** 创建时间 */
    private Timestamp createdDate;
    /** 创建者 */
    private String createBy;
    /** 更新时间 */
    private Timestamp updateDate;
    /** 更新者 */
    private String updateBy;
    /** 是否有效 */
    private boolean valid;

    /** 构造方法 */
    public AbstractEntity() {
    }

    public AbstractEntity(String remark, String remark1,
                          String remark2, String remark3,
                          Timestamp createdDate, String createBy,
                          Timestamp updateDate, String updateBy,
                          boolean valid) {
        this.remark = remark;
        this.remark1 = remark1;
        this.remark2 = remark2;
        this.remark3 = remark3;
        this.createdDate = createdDate;
        this.createBy = createBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
        this.valid = valid;
    }

    /**
     * 重写toString
     * @return 抽象类属性值
     */
    @Override
    public String toString() {
        return "AbstractEntity{" +
                "remark='" + remark + '\'' +
                ", remark1='" + remark1 + '\'' +
                ", remark2='" + remark2 + '\'' +
                ", remark3='" + remark3 + '\'' +
                ", createdDate=" + createdDate +
                ", createBy='" + createBy + '\'' +
                ", updateDate=" + updateDate +
                ", updateBy='" + updateBy + '\'' +
                ", valid=" + valid +
                '}';
    }

    /** getter/setter start*/
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
