package com.zhang.sevice.impl;

import com.github.pagehelper.PageHelper;
import com.zhang.dao.IRoleDao;
import com.zhang.domain.Role;
import com.zhang.sevice.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> findAll(int page,int pageSize) {
		PageHelper.startPage(page,pageSize);
		List<Role> roleList = roleDao.findAll();
		return roleList;
	}

	/**
	 * 添加角色
	 * @param role
	 */
	public void save(Role role) {
		roleDao.save(role);
	}

	/**
	 * 根据用户id查询其所拥有角色
	 * @param userId
	 * @return
	 */
	public List<Role> findNotAll(String userId) {
		List<Role> roleList = roleDao.findNotAll(userId);
		return roleList;
	}

	/**
	 *根据角色id查询其拥有的权限
	 * @param roleId
	 * @return
	 */
	public Role findRoleByIdAndAllpermission(String roleId) {
		Role role = roleDao.findRoleByIdAndAllpermission(roleId);
		return role;
	}

	/**
	 * 添加权限给角色
	 * @param roleId
	 * @param ids
	 */
	public void addPermissionToRole(String roleId, String[] ids) {
		for (String permissionId : ids) {
			roleDao.addPermissionToRole(roleId,permissionId);
		}

	}

	/**
	 * 根据roleid查询角色
	 * @param roleId
	 * @return
	 */
	public Role findById(String roleId) {
		Role role = roleDao.findById(roleId);
		return role;
	}

	/**
	 * 根据userId查询其拥有角色
	 * @param userId
	 * @return
	 */
	public List<Role> findByIdAllRole(String userId) {
		List<Role> roleList = roleDao.findByUserId(userId);
		return roleList;
	}
}
