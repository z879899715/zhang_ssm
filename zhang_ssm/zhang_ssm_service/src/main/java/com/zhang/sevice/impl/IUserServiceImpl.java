package com.zhang.sevice.impl;

import com.github.pagehelper.PageHelper;
import com.zhang.dao.IUserDao;
import com.zhang.domain.Role;
import com.zhang.domain.UserInfo;
import com.zhang.sevice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class IUserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * 使用spring-security进行认证
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserInfo userInfo = userDao.findByName(username);

		User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
		System.out.println(userInfo.getUsername());
		return user;
	}

	public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

		for (Role role : roles) {
			System.out.println("角色名称:ROLE_"+role.getRoleName());
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		}
		return authorities;
	}

	/**
	 * 查询所有用户
	 * @return
	 */
	public List<UserInfo> findAll(int page,int pageSize) {
		PageHelper.startPage(page,pageSize);
		List<UserInfo> userList = userDao.findAll();
		return userList;
	}

	/**
	 * 添加用户
	 * @param userInfo
	 * @throws Exception
	 */
	public void save(UserInfo userInfo) throws Exception {
		//给密码加密
		userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
		userDao.save(userInfo);
	}

	/**
	 * 根据id查找用户所有信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UserInfo findById(String id) throws Exception {
		UserInfo userInfo = userDao.findById(id);

		return userInfo;
	}

	/**
	 * 根据用户id查询用户
	 * @param id
	 * @return
	 */
	public UserInfo findUserByIdAndAllRole(String id) {
		UserInfo userInfo = userDao.findUserByIdAndAllRole(id);
		return userInfo;
	}

	/**
	 * 给用户添加权限
	 * @param userId
	 * @param ids
	 */
	public void addRoleToUser(String userId, String[] ids) {

		for (String roleId : ids) {
			userDao.addRoleToUser(userId,roleId);
		}

	}

	/**
	 * 根据用户id和角色id解除绑定
	 * @param roleId
	 * @param userId
	 */
	public void deleteRole(String roleId, String userId) {
		userDao.deleteRole(roleId,userId);
	}

}
