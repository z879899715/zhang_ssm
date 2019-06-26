package com.zhang.controller;

import com.github.pagehelper.PageInfo;
import com.zhang.domain.Permission;
import com.zhang.sevice.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequestMapping("/permission")
@Controller
public class PermissionController {

	@Autowired
	private IPermissionService permissionService;

	/**
	 * 查询所有权限
	 * @param model
	 * @return
	 */
	@RequestMapping("/findAll")
		@PreAuthorize("hasRole('ROLE_zzj')")
//	@RolesAllowed({"zzj"})
	public ModelAndView findAll(@RequestParam(name = "page",required = false) Integer page, @RequestParam(name = "pageSize",required = false) Integer pageSize, ModelAndView model){
		if(page ==null || page == 0){
			page = 1;
		}
		if(pageSize ==null || pageSize == 0){
			pageSize = 3;
		}
		List<Permission> permissionList = permissionService.findAll(page,pageSize);
		PageInfo pageInfo = new PageInfo(permissionList);
		model.addObject("pageInfo",pageInfo);
		model.setViewName("permission-list");
		return model;
	}

	/**
	 * 添加权限
	 * @param permission
	 * @return
	 */
	@RequestMapping("/save")
		@PreAuthorize("hasRole('ROLE_zzj')")
//	@RolesAllowed({"zzj"})
	public String save(Permission permission){
		permissionService.save(permission);
		return "redirect:findAll.do";
	}
}
