package com.zhang.controller;

import com.github.pagehelper.PageInfo;
import com.zhang.domain.SysLog;
import com.zhang.sevice.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

	@Autowired
	private ISysLogService logService;


	@RequestMapping("/findAll")
	public ModelAndView findAll(@RequestParam(name = "page") Integer page, @RequestParam(name = "pageSize") Integer pageSize,ModelAndView modelAndView){

		List<SysLog> sysLogList = logService.findAll(page,pageSize);
		PageInfo pageInfo = new PageInfo(sysLogList);
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("syslog-list");
		return modelAndView;
	}
}
