package com.zhang.sevice.impl;

import com.github.pagehelper.PageHelper;
import com.zhang.dao.ISysLogDao;
import com.zhang.domain.SysLog;
import com.zhang.sevice.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ISysLogServiceImpl implements ISysLogService {

	@Autowired
	private ISysLogDao sysLogDao;

	/**
	 * 存储日志信息
	 * @param sysLog
	 */
	public void save(SysLog sysLog) {
		sysLogDao.save(sysLog);
	}

	/**
	 * 查询所有日志
	 * @return
	 */
	public List<SysLog> findAll(int page,int pageSize) {
		PageHelper.startPage(page,pageSize);
		List<SysLog> sysLogList = sysLogDao.findAll();
		return sysLogList;
	}
}
