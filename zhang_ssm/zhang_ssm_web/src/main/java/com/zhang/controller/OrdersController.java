package com.zhang.controller;


import com.github.pagehelper.PageInfo;
import com.zhang.domain.Orders;
import com.zhang.sevice.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private IOrdersService ordersService;

	@RequestMapping("/findAll")
	public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "pageSize",required = true,defaultValue = "4") Integer pageSize, ModelAndView model){
		List<Orders> ordersList = ordersService.findAll(page, pageSize);
		PageInfo pageInfo = new PageInfo(ordersList);
		model.addObject("pageInfo",pageInfo);
		model.setViewName("orders-list");
		return model;
	}


	@RequestMapping("/findById")
	public ModelAndView findById(@RequestParam(name="id") String id,ModelAndView model){
		Orders orders = ordersService.findById(id);

		model.addObject("orders",orders);
		model.setViewName("orders-show");
		return model;
	}
}
