package com.zhang.dao;

import com.zhang.domain.Role;
import com.zhang.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface IUserDao {

	@Select("select * from users where username = #{username}")
	@Results({
			@Result(id = true,column = "id",property = "id"),
			@Result(column = "username",property = "username"),
			@Result(column = "email",property = "email"),
			@Result(column = "password",property = "password"),
			@Result(column = "phoneNum",property = "phoneNum"),
			@Result(column = "status",property = "status"),
			@Result(column = "id",property = "roles",javaType = List.class,
					many = @Many(select = "com.zhang.dao.IRoleDao.findByUserId")
			)
	})
	public UserInfo findByName(String username);

	@Select("select * from users")
	public List<UserInfo> findAll();

	@Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
	public void save(UserInfo userInfo);

	@Select("select * from users where id = #{id}")
	@Results({
			@Result(id = true,column = "id",property = "id"),
			@Result(column = "username",property = "username"),
			@Result(column = "email",property = "email"),
			@Result(column = "password",property = "password"),
			@Result(column = "phoneNum",property = "phoneNum"),
			@Result(column = "statusStr",property = "statusStr"),
			@Result(column = "id",property = "roles",javaType = List.class,
			many=@Many(select = "com.zhang.dao.IRoleDao.findByUserId")
			)
	})
	public UserInfo findById(String id);

	@Select("select * from users where id = #{id}")
	public UserInfo findUserByIdAndAllRole(String id);

	@Insert("insert into users_role values(#{userId},#{roleId})")
	public void addRoleToUser(@Param("userId")String userId,@Param("roleId") String roleId);

	@Select("select * from users where id in(select userId from users_role where roleId = #{roleId})")
	public List<UserInfo> findByRoleId(String roleId);


	@Delete("delete from users_role where roleId = #{roleId} and userId = #{userId}")
	public void deleteRole(@Param("roleId") String roleId, @Param("userId") String userId);
}
