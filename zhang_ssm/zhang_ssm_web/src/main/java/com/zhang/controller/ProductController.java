package com.zhang.controller;

import com.github.pagehelper.PageInfo;
import com.zhang.domain.Product;
import com.zhang.sevice.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;

	@RequestMapping("/findAll")
	public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "pageSize",required = true,defaultValue = "4" )Integer pageSize, ModelAndView model){

		List<Product>  productList = productService.findAll(page,pageSize);
		PageInfo PpageInfo = new PageInfo(productList);
		model.addObject("PpageInfo",PpageInfo);
		model.setViewName("product-list");
		return model;
	}

	@RequestMapping("/save")
	public String save(Product product){
		productService.save(product);

		return "redirect:findAll.do";
	}

}
