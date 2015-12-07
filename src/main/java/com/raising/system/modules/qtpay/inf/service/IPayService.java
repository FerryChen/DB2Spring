package com.raising.system.modules.qtpay.inf.service;

import java.util.List;

import com.raising.system.modules.qtpay.vo.Pay;

public interface IPayService {

	void insertPay(Pay pay);
	
	List<Pay> selectPay(Pay pay);
}
