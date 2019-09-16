package com.ky.gps.entity;

import java.sql.Timestamp;

/**
 * @author Daye
 * 校车实体类
 */
public class SbBus extends AbstractEntity {

    /** 位置id */
    private Integer id;
    /** 座位数 */
    private Integer ssbSeatNum;
    /** 车辆类型 */
    private String ssbBusType;
    /** 车牌号 */
    private String ssbPlateNumber;
    /** 驾驶员 */
    private String ssbDriverName;
    /** 联系电话 */
    private String ssbDriverTel;

    /** 无参构造方法 */
    public SbBus() {
    }

    /** 有参构造方法-id */
    public SbBus(Integer id) {
        this.id = id;
    }

    /** 有参构造方法-自身属性 */
    public SbBus(Integer id, Integer ssbSeatNum,
                 String ssbBusType, String ssbPlateNumber,
                 String ssbDriverName, String ssbDriverTel) {
        this.id = id;
        this.ssbSeatNum = ssbSeatNum;
        this.ssbBusType = ssbBusType;
        this.ssbPlateNumber = ssbPlateNumber;
        this.ssbDriverName = ssbDriverName;
        this.ssbDriverTel = ssbDriverTel;
    }

    /** 有参构造方法-所有属性 */
    public SbBus(String remark, String remark1,
                 String remark2, String remark3,
                 Timestamp createdDate, String createdBy,
                 Timestamp updatedDate, String updatedBy,
                 Boolean valid, Integer id, Integer ssbSeatNum,
                 String ssbBusType, String ssbPlateNumber,
                 String ssbDriverName, String ssbDriverTel) {
        super(remark, remark1, remark2, remark3, createdDate, createdBy, updatedDate, updatedBy, valid);
        this.id = id;
        this.ssbSeatNum = ssbSeatNum;
        this.ssbBusType = ssbBusType;
        this.ssbPlateNumber = ssbPlateNumber;
        this.ssbDriverName = ssbDriverName;
        this.ssbDriverTel = ssbDriverTel;
    }

    /**
     * 重写toString
     * @return 属性名+属性值
     */
    @Override
    public String toString() {
        return "SbBus{" +
                "id=" + id +
                ", ssbSeatNum=" + ssbSeatNum +
                ", ssbBusType='" + ssbBusType + '\'' +
                ", ssbPlateNumber='" + ssbPlateNumber + '\'' +
                ", ssbDriverName='" + ssbDriverName + '\'' +
                ", ssbDriverTel='" + ssbDriverTel + '\'' +
                '}';
    }

    /** getter/setter */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSsbSeatNum() {
        return ssbSeatNum;
    }

    public void setSsbSeatNum(Integer ssbSeatNum) {
        this.ssbSeatNum = ssbSeatNum;
    }

    public String getSsbBusType() {
        return ssbBusType;
    }

    public void setSsbBusType(String ssbBusType) {
        this.ssbBusType = ssbBusType;
    }

    public String getSsbPlateNumber() {
        return ssbPlateNumber;
    }

    public void setSsbPlateNumber(String ssbPlateNumber) {
        this.ssbPlateNumber = ssbPlateNumber;
    }

    public String getSsbDriverName() {
        return ssbDriverName;
    }

    public void setSsbDriverName(String ssbDriverName) {
        this.ssbDriverName = ssbDriverName;
    }

    public String getSsbDriverTel() {
        return ssbDriverTel;
    }

    public void setSsbDriverTel(String ssbDriverTel) {
        this.ssbDriverTel = ssbDriverTel;
    }
}
