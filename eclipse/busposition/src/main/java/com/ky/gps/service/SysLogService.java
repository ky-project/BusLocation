package com.ky.gps.service;

import com.ky.gps.entity.SysLog;

/**
 * @author Daye
 * 用户操作记录Service接口
 */
public interface SysLogService {
    /**
     * 插入用户操作记录
     * @param sysLog 用户信息和操作记录
     */
    void saveSysLog(SysLog sysLog);
}
