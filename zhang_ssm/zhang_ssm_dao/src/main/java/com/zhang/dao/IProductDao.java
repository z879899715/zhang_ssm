package com.zhang.dao;

import com.zhang.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {

	@Insert("insert into product (productNum,productName,cityName,departureTime, productPrice,productDesc,productStatus) values" +
			" (#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
	public void save(Product product);

	@Select("select * from product")
	public List<Product> findAll();

	@Select("select * from product where id = #{id}")
	public Product findById(String id);
}
