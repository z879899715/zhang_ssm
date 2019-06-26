package com.zhang.sevice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.dao.IOrdersDao;
import com.zhang.domain.Orders;
import com.zhang.sevice.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IOrdersServiceImpl implements IOrdersService {

	@Autowired
	private IOrdersDao ordersDao;

	/**
	 * 查询所有订单
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Orders> findAll(int page, int pageSize) {
		PageHelper.startPage(page,pageSize);
		List<Orders> ordersList = ordersDao.finAll();
		return ordersList;
	}

	/**
	 * 根据id查询订单
	 * @param id
	 * @return
	 */
	public Orders findById(String id) {
		Orders orders = ordersDao.findById(id);
		return orders;
	}
}
