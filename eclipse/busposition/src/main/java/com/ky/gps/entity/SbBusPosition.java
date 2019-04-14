package com.ky.gps.entity;

import java.sql.Timestamp;

/**
 * @author Daye
 * 校车位置信息
 */
public class SbBusPosition extends AbstractEntity {

    /** id */
    private Integer id;
    /** 所属校车 */
    private SbBus sbBus;
    /** 记录时间 */
    private Timestamp sbpRecodeTime;
    /** 实时经度 */
    private Float sbpLongitude;
    /** 实时纬度 */
    private Float sbpLatitude;
    /** 速度 */
    private Float sbpVelocity;
    /** 方向角 */
    private Float sbpDirection;

    /** 无参构造方法 */
    public SbBusPosition() {
    }

    /** 有参构造方法-自身属性 */
    public SbBusPosition(Integer id, SbBus sbBus,
                         Timestamp sbpRecodeTime, Float sbpLongitude,
                         Float sbpLatitude, Float sbpVelocity,
                         Float sbpDirection) {
        this.id = id;
        this.sbBus = sbBus;
        this.sbpRecodeTime = sbpRecodeTime;
        this.sbpLongitude = sbpLongitude;
        this.sbpLatitude = sbpLatitude;
        this.sbpVelocity = sbpVelocity;
        this.sbpDirection = sbpDirection;
    }

    /** 有参构造方法-所有属性 */
    public SbBusPosition(String remark, String remark1,
                         String remark2, String remark3,
                         Timestamp createdDate, String createdBy,
                         Timestamp updatedDate, String updatedBy,
                         Boolean valid, Integer id, SbBus sbBus,
                         Timestamp sbpRecodeTime, Float sbpLongitude,
                         Float sbpLatitude, Float sbpVelocity,
                         Float sbpDirection) {
        super(remark, remark1, remark2, remark3, createdDate, createdBy, updatedDate, updatedBy, valid);
        this.id = id;
        this.sbBus = sbBus;
        this.sbpRecodeTime = sbpRecodeTime;
        this.sbpLongitude = sbpLongitude;
        this.sbpLatitude = sbpLatitude;
        this.sbpVelocity = sbpVelocity;
        this.sbpDirection = sbpDirection;
    }

    /**
     * 重写toString
     * @return 对象属性名+属性值
     */
    @Override
    public String toString() {
        return "SbBusPosition{" +
                "id=" + id +
                ", sbBus=" + sbBus +
                ", sbpRecodeTime=" + sbpRecodeTime +
                ", sbpLongitude=" + sbpLongitude +
                ", sbpLatitude=" + sbpLatitude +
                ", sbpVelocity=" + sbpVelocity +
                ", sbpDirection=" + sbpDirection +
                '}';
    }

    /** getter/setter */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SbBus getSbBus() {
        return sbBus;
    }

    public void setSbBus(SbBus sbBus) {
        this.sbBus = sbBus;
    }

    public Timestamp getSbpRecodeTime() {
        return sbpRecodeTime;
    }

    public void setSbpRecodeTime(Timestamp sbpRecodeTime) {
        this.sbpRecodeTime = sbpRecodeTime;
    }

    public Float getSbpLongitude() {
        return sbpLongitude;
    }

    public void setSbpLongitude(Float sbpLongitude) {
        this.sbpLongitude = sbpLongitude;
    }

    public Float getSbpLatitude() {
        return sbpLatitude;
    }

    public void setSbpLatitude(Float sbpLatitude) {
        this.sbpLatitude = sbpLatitude;
    }

    public Float getSbpVelocity() {
        return sbpVelocity;
    }

    public void setSbpVelocity(Float sbpVelocity) {
        this.sbpVelocity = sbpVelocity;
    }

    public Float getSbpDirection() {
        return sbpDirection;
    }

    public void setSbpDirection(Float sbpDirection) {
        this.sbpDirection = sbpDirection;
    }
}
