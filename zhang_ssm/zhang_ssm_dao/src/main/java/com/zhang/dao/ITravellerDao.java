package com.zhang.dao;

import com.zhang.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {


	/**
	 * 根据userId通过中间表查询出所有role
	 * @param id
	 * @return
	 */
	@Select("select * from traveller where id in(select travellerId from order_traveller where orderid = #{id}) ")
	public List<Traveller> findByOrdersId(String id);
}
