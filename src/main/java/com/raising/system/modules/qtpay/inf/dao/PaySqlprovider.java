package com.raising.system.modules.qtpay.inf.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;

import com.raising.system.modules.qtpay.vo.Pay;



public class PaySqlprovider {

	@SuppressWarnings("deprecation")
	public static String addPay(Pay pay) {
		BEGIN();
		INSERT_INTO("TESTA");
		VALUES("ID", "#{id,javaType=string,jdbcType=VARCHAR}");
		VALUES("NAME", "#{name,javaType=string,jdbcType=VARCHAR}");
		return SQL();
	}
	
	public static String selectList(Pay pay){
		BEGIN();
		SELECT("ID");
		SELECT("NAME");
		FROM("TESTA");
		return SQL();
	}

}
