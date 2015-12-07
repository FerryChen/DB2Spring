package com.raising.system.modules.qtpay.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.raising.system.modules.BaseController;
import com.raising.system.modules.qtpay.inf.service.IPayService;
import com.raising.system.modules.qtpay.vo.Pay;


@Controller
public class PayController  extends BaseController  {
	
	private IPayService	payService;
	
	
	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public void addPay(HttpServletRequest request) {
	    Pay pay=new Pay();
		pay.setId("33a");
		pay.setName("bbb");
		payService.insertPay(pay);
	}

	
	@RequestMapping(value = "getList", method = { RequestMethod.GET })
	public void getList(HttpServletRequest request) {
	    Pay pay=new Pay();
		List<Pay> list = payService.selectPay(pay);
		for (Pay pay2 : list) {
			System.out.println("-----"+pay2.getId()+"---"+pay2.getName()+"-------");
		}
		System.out.println("-------------------");
	}

	@Resource(name="payService")
	public void setPayService(IPayService payService) {
		this.payService = payService;
	}
	

}
