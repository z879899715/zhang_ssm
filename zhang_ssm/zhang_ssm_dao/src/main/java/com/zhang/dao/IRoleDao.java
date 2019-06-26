package com.zhang.dao;

import com.zhang.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleDao {

	/**
	 * 根据用户id查询角色
	 * @param userId
	 * @return
	 */
	@Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
	@Results({
			@Result(id = true,column = "id",property = "id"),
			@Result(column = "roleName",property = "roleName"),
			@Result(column = "roleDesc",property = "roleDesc"),
			@Result(column = "id",property = "permissions",javaType = List.class,
			many = @Many(select = "com.zhang.dao.IPermissionDao.findByRoleId")
			)

	})
	public List<Role> findByUserId(String userId);

	/**
	 * 查询所有角色
	 * @return
	 */
	@Select("select * from role")
	public List<Role> findAll();

	/**
	 * 添加角色
	 * @param role
	 */
	@Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
	public void save(Role role);

	/**
	 * 查询此用户没有的角色
	 * @param userId
	 * @return
	 */
	@Select("select * from role where id not in(select roleId from users_role where userId = #{userId})")
	public List<Role> findNotAll(String userId);

	/**
	 * 根据角色id查询角色
	 * @param roleId
	 * @return
	 */
	@Select("select * from role where id = #{roleId}")
	public Role findRoleByIdAndAllpermission(String roleId);

	/**
	 * 添加权限给角色
	 * @param roleId
	 * @param permissionId
	 */
	@Insert("insert into role_permission values(#{roleId},#{permissionId})")
	public void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

	/**
	 * 根据角色ID查询
	 * @param roleId
	 * @return
	 */
	@Select("select * from role where id = #{id}")
	@Results({
			@Result(id = true,column = "id",property = "id"),
			@Result(column = "roleName",property = "roleName"),
			@Result(column = "roleDesc",property = "roleDesc"),
			@Result(column = "id",property = "permissions",javaType = List.class,
					many=@Many(select = "com.zhang.dao.IPermissionDao.findByRoleId")
			),
			@Result(column = "id",property = "users",javaType = List.class,
			many = @Many(select = "com.zhang.dao.IUserDao.findByRoleId")
			)
	})
	public Role findById(String roleId);
}
