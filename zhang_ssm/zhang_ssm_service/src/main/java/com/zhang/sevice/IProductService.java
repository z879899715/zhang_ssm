package com.zhang.sevice;

import com.zhang.domain.Product;

import java.util.List;

public interface IProductService {
	public void save(Product product);

	public List<Product> findAll(int page,int pageSize);
}
