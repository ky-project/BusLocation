package com.ky.gps.service.impl;

import com.ky.gps.dao.SysLogDao;
import com.ky.gps.entity.SysLog;
import com.ky.gps.service.SysLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Daye
 * 用户操作记录Service接口实现类
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogDao sysLogDao;

    @Async
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveSysLog(SysLog sysLog){
        sysLogDao.saveSysLog(sysLog);
    }
}
