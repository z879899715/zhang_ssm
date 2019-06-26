package com.zhang.controller;

import com.github.pagehelper.PageInfo;
import com.zhang.domain.Permission;
import com.zhang.domain.Role;
import com.zhang.sevice.IPermissionService;
import com.zhang.sevice.IRoleService;
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
@RequestMapping("/role")

public class RoleController {

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IPermissionService permissionService;

	/**
	 * 查询所有角色
	 * @param model
	 * @return
	 */
	@RequestMapping("/findAll")
	@PreAuthorize("hasRole('ROLE_zzj')")
//	@RolesAllowed({"zzj"})
	public ModelAndView findAll(@RequestParam(name = "page",required = false) Integer page, @RequestParam(name = "pageSize",required = false) Integer pageSize,ModelAndView model){
		if(page ==null || page == 0){
			page = 1;
		}
		if(pageSize ==null || pageSize == 0){
			pageSize = 3;
		}
		List<Role> roleList = roleService.findAll(page,pageSize);
		PageInfo pageInfo = new PageInfo(roleList);
		model.addObject("pageInfo",pageInfo);
		model.setViewName("role-list");
		return model;
	}


	@RequestMapping("/save")
		@PreAuthorize("hasRole('ROLE_zzj')")
//	@RolesAllowed({"zzj"})
	public String save(Role role){

		roleService.save(role);
		return "redirect:findAll.do";
	}

	/**
	 * 根据用户id查询用户
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping("/findUserByIdAndAllRole")
		@PreAuthorize("hasRole('ROLE_zzj')")
//	@RolesAllowed({"zzj"})
	public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id")String roleId, ModelAndView model){
		Role role = roleService.findRoleByIdAndAllpermission(roleId);
		List<Permission> permissionList = permissionService.findNotAll(roleId);
		model.addObject("role",role);
		model.addObject("permissionList",permissionList);
		model.setViewName("role-permission-add");
		return model;
	}


	@RequestMapping("/addPermissionToRole")
		@PreAuthorize("hasRole('ROLE_zzj')")
//	@RolesAllowed({"zzj"})
	public String addPermissionToRole(@RequestParam(name = "roleId")String roleId,@RequestParam(name = "ids") String[] ids){
		if(ids.length>0 && ids != null){

			roleService.addPermissionToRole(roleId,ids);
		}

		return "redirect:findAll.do";
	}


	@RequestMapping("/findById")
		@PreAuthorize("hasRole('ROLE_zzj')")
//	@RolesAllowed({"zzj"})
	public ModelAndView findById(@RequestParam(name = "id") String roleId,ModelAndView model){
		Role role = roleService.findById(roleId);
		model.addObject("role",role);
		model.setViewName("role-show1");
		return model;
	}
}
