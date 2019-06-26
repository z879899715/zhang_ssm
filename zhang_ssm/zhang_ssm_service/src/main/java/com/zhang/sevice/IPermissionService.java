package com.zhang.sevice;

import com.zhang.domain.Permission;

import java.util.List;

public interface IPermissionService {
	public List<Permission> findAll(int page,int pageSize);

	public void save(Permission permission);

	public List<Permission> findNotAll(String roleId);
}
