package com.raising.system.modules.qtpay.inf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

import com.raising.db.DataSource;
import com.raising.system.framework.mybatis.IBaseMapper;
import com.raising.system.modules.qtpay.inf.dao.PaySqlprovider;
import com.raising.system.modules.qtpay.vo.Pay;

public interface IPayMapper  extends IBaseMapper {

	 
	@InsertProvider(type = PaySqlprovider.class, method = "addPay", staticMethod = true)
	@ResultType(Integer.class)
	@DataSource("master")
	public int add(Pay user);
	
	@SelectProvider(type=PaySqlprovider.class,method="selectList",staticMethod=true)
	@ResultType(Pay.class)
	@DataSource("slave")
	public List<Pay> selectList(Pay pay);

}
