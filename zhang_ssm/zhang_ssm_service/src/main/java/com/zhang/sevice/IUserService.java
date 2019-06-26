package com.zhang.sevice;

import com.zhang.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

	public List<UserInfo> findAll(int page,int pageSize);

	public void save(UserInfo userInfo) throws Exception;

	public UserInfo findById(String id) throws Exception;

	public UserInfo findUserByIdAndAllRole(String id);

	public void addRoleToUser(String userId, String[] ids);

	public void deleteRole(String roleId, String userId);
}
