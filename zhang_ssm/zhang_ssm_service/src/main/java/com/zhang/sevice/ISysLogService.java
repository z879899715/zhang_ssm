package com.zhang.sevice;

import com.zhang.domain.SysLog;

import java.util.List;

public interface ISysLogService {

	public void save(SysLog sysLog);

	public List<SysLog> findAll(int page,int pageSize);
}
