package com.congzer.pms.service;

import com.congzer.pms.domain.SysLog;

import java.util.List;

public interface SysLogService {

    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll() throws Exception;
}
