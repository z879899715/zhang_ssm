package com.zhang.dao;

import com.zhang.domain.Member;
import com.zhang.domain.Orders;
import com.zhang.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

	@Select("select * from orders")
	@Results({

			@Result(id = true, column = "id",property = "id"),
			@Result(column = "orderNum",property = "orderNum"),
			@Result(column = "orderTime",property = "orderTimeStr"),
			@Result(column = "orderStatus",property = "orderStatus"),
			@Result(column = "peopleCount",property = "peopleCount"),
			@Result(column = "payType",property = "payType"),
			@Result(column = "orderDesc",property = "orderDesc"),
			@Result(
					column = "productId",property = "product",javaType = Product.class,
					one = @One(select = "com.zhang.dao.IProductDao.findById")
			)
	})
	public List<Orders> finAll();

	@Select("select * from orders where id =#{id}")
	@Results({

			@Result(id = true, column = "id",property = "id"),
			@Result(column = "orderNum",property = "orderNum"),
			@Result(column = "orderTime",property = "orderTimeStr"),
			@Result(column = "orderStatus",property = "orderStatus"),
			@Result(column = "peopleCount",property = "peopleCount"),
			@Result(column = "payType",property = "payType"),
			@Result(column = "orderDesc",property = "orderDesc"),
			@Result(
					column = "productId",property = "product",javaType = Product.class,
					one = @One(select = "com.zhang.dao.IProductDao.findById")
			),
			@Result(
					column = "memberId",property = "member",javaType = Member.class,
					one = @One(select = "com.zhang.dao.IMemberDao.findById")
			),
			@Result(
					column = "id",property = "travellers",javaType = List.class,
					many = @Many(select = "com.zhang.dao.ITravellerDao.findByOrdersId")
			)
	})
	Orders findById(String id);
}
