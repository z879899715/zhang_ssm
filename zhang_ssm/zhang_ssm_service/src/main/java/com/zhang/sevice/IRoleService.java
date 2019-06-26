package com.zhang.sevice;

import com.zhang.domain.Role;

import java.util.List;

public interface IRoleService {
	public List<Role> findAll(int page,int pageSize);

	public void save(Role role);

	public List<Role> findNotAll(String userId);

	public Role findRoleByIdAndAllpermission(String roleId);

	public void addPermissionToRole(String roleId, String[] ids);

	public Role findById(String roleId);

	public List<Role> findByIdAllRole(String userId);
}
