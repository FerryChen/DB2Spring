package com.raising.system.modules.qtpay.impl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.raising.system.modules.qtpay.inf.mapper.IPayMapper;
import com.raising.system.modules.qtpay.inf.service.IPayService;
import com.raising.system.modules.qtpay.vo.Pay;

@DependsOn(value = "IPayMapper")
@Service(value="payService")
public class PayServiceImpl implements IPayService {
	@Autowired
	private IPayMapper payMapper;

	@Override
	public void insertPay(Pay pay) {
		payMapper.add(pay);
	}
	
	
	@Override
	public List<Pay> selectPay(Pay pay) {
		return   payMapper.selectList(pay);
	}
	





	


}
