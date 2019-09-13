package com.ky.gps.dao;

/**
 * @author Daye
 */
public interface SbBusPositionHisDao {

    /**
     * 将当前位置表中的所有记录插入到历史表中
     */
    void insertFromSbBusPosition();

}
