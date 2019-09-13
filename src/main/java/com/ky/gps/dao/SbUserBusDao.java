package com.ky.gps.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author Daye
 * 用户和路线多对多的Dao数据访问层
 */
public interface SbUserBusDao {

    /**
     * 根据用户id删除该用户和路线对应的记录
     * 不是真删除，而是将Valid设为0，即无效
     *
     * @param userId   用户id
     * @param updateBy 更新者的workId
     */
    void deleteUserBusByUserId(@Param("userId") Integer userId, @Param("updateBy") String updateBy);
}
