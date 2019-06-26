package com.zhang.controller;

import com.github.pagehelper.PageInfo;
import com.zhang.domain.Role;
import com.zhang.domain.UserInfo;
import com.zhang.sevice.IRoleService;
import com.zhang.sevice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;

	/**
	 * 查询所有用户
	 * @param
	 * @return
	 */
	@RequestMapping("/findAll")
	@PreAuthorize("hasRole('ROLE_zzj')")
//	@RolesAllowed({"zzj"})
	public ModelAndView findAll(@RequestParam(name = "page",required = false) Integer page, @RequestParam(name = "pageSize",required = false) Integer pageSize,ModelAndView modelAndView){

		if(page ==null || page == 0){
			page = 1;
		}
		if(pageSize ==null || pageSize == 0){
			pageSize = 3;
		}
		List<UserInfo> userList = userService.findAll(page,pageSize);
		PageInfo pageInfo = new PageInfo(userList);
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("user-list");
		return modelAndView;
	}

	/**
	 * 添加用户
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("/save")
	@PreAuthorize("hasRole('ROLE_zzj')")
//	@RolesAllowed({"zzj"})
	public String save(UserInfo userInfo) throws Exception {

		userService.save(userInfo);

		return "redirect:findAll.do";
	}

	/**
	 * 根据id查询其与其关联的
	 * @param id
	 * @param
	 * @return
	 */
	@RequestMapping("/findById")
	@PreAuthorize("hasRole('ROLE_zzj')")
//	@RolesAllowed({"zzj"})
	public ModelAndView findById(@RequestParam(name = "id") String id,ModelAndView modelAndView) throws Exception {

		UserInfo userInfo = userService.findById(id);
		modelAndView.addObject("user",userInfo);
		modelAndView.setViewName("user-show1");
		return modelAndView;
	}

	/**
	 * 根据用户id查询用户
	 * @param userId
	 * @param
	 * @return
	 */
	@RequestMapping("/findUserByIdAndAllRole")
	@PreAuthorize("hasRole('ROLE_zzj')")
//	@RolesAllowed({"zzj"})
	public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id")String userId,ModelAndView modelAndView){
		UserInfo user = userService.findUserByIdAndAllRole(userId);
		List<Role> roleList = roleService.findNotAll(userId);
		modelAndView.addObject("user",user);
		modelAndView.addObject("roleList",roleList);
		modelAndView.setViewName("user-role-add");
		return modelAndView;
	}

	/**
	 * 给用户添加角色
	 * @param userId
	 * @param ids
	 * @return
	 */
	@RequestMapping("/addRoleToUser")
	@PreAuthorize("hasRole('ROLE_zzj')")
//	@RolesAllowed({"zzj"})
	public String addRoleToUser(@RequestParam(name = "userId")String userId,@RequestParam(name = "ids") String[] ids){
		if(ids.length>0 && ids != null){

			userService.addRoleToUser(userId,ids);
		}

		return "redirect:findAll.do";
	}

	/**
	 * 根据角色Id查询其拥有角色
	 * @param userId
	 * @return
	 */
	@RequestMapping("/findRoleByUserId")
	@PreAuthorize("hasRole('ROLE_zzj')")
//	@RolesAllowed({"zzj"})
	public ModelAndView findRoleByUserId(@RequestParam(name = "id")String userId,ModelAndView modelAndView){
		UserInfo user = userService.findUserByIdAndAllRole(userId);
		List<Role> roleList = roleService.findByIdAllRole(userId);
		modelAndView.addObject("user",user);
		modelAndView.addObject("roleList",roleList);
		modelAndView.setViewName("user-role-delete");
		return modelAndView;
	}
	/**
	 * 根据用户id和角色id解除绑定
	 */
	@RequestMapping("/deleteRole")
	@PreAuthorize("hasRole('ROLE_zzj')")
//	@RolesAllowed({"zzj"})
	public ModelAndView deleteRole(@RequestParam(name = "roleId") String roleId,@RequestParam(name = "userId") String UserId,ModelAndView modelAndViewl){
		userService.deleteRole(roleId,UserId);
		modelAndViewl.addObject("id",UserId);
		modelAndViewl.setViewName("redirect:findRoleByUserId.do");
		return modelAndViewl;
	}
}
