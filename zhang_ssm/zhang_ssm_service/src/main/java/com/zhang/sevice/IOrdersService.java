package com.zhang.sevice;

import com.zhang.domain.Orders;

import java.util.List;

public interface IOrdersService {


	public List<Orders> findAll(int page, int pageSize);

	public Orders findById(String id);
}
