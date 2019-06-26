package com.zhang.dao;

import com.zhang.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
	/**
	 * 根据角色Id查询资源权限
	 * @param roleId
	 * @return
	 */
	@Select("select * from permission where id in(select permissionId from role_permission where roleId = #{roleId})")
	public List<Permission> findByRoleId(String roleId);

	/**
	 * 查询所有权限
	 * @return
	 */
	@Select("select * from permission")
	public List<Permission> findAll();

	/**
	 * 添加权限
	 * @param permission
	 */
	@Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
	public void save(Permission permission);

	@Select("select * from permission where id not in(select permissionId from role_permission where roleId = #{roleId})")
	public List<Permission> findNotAll(String roleId);

}
