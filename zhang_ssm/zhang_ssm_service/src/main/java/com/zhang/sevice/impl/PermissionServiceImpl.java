package com.zhang.sevice.impl;

import com.github.pagehelper.PageHelper;
import com.zhang.dao.IPermissionDao;
import com.zhang.domain.Permission;
import com.zhang.sevice.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private IPermissionDao permissionDao;

	/**
	 * 查询所有权限
	 * @return
	 */
	public List<Permission> findAll(int page,int pageSize) {
		PageHelper.startPage(page,pageSize);
		List<Permission> permissionList = permissionDao.findAll();
		return permissionList;
	}

	/**
	 * 添加权限
	 * @param permission
	 */
	public void save(Permission permission) {
		permissionDao.save(permission);
	}
	public List<Permission> findNotAll(String roleId) {
		List<Permission> permissionList = permissionDao.findNotAll(roleId);
		return permissionList;
	}
}
