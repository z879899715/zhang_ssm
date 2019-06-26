package com.zhang.sevice.impl;

import com.github.pagehelper.PageHelper;
import com.zhang.dao.IProductDao;
import com.zhang.domain.Product;
import com.zhang.sevice.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao productDao;
	/**
	 * 添加产品
	 * @param product
	 */
	public void save(Product product) {
		productDao.save(product);
	}

	/**
	 * 查询所有产品
	 * @return
	 */
	public List<Product> findAll(int page, int pageSize) {
		PageHelper.startPage(page,pageSize);
		List<Product> productList = productDao.findAll();
		return productList;
	}
}
