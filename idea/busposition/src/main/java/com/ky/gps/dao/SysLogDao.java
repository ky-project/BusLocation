package com.ky.gps.dao;

import com.ky.gps.entity.SysLog;
import org.springframework.stereotype.Repository;

/**
 * @author Daye
 * 操作记录Dao
 */
@Repository
public interface SysLogDao {

    /**
     * 插入用户操作记录
     * @param sysLog 用户信息和操作记录
     */
    void saveSysLog(SysLog sysLog);
}
