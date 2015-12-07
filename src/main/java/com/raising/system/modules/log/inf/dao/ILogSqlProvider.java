/**
 * 
 * 项目名称：PM <br/>
 * 类名称：SqlProvider.java <br/>
 * 类描述：TODO<br/>
 * 创建人：d407 <br/>
 * 创建时间：20132013-6-4下午1:18:02 <br/>
 * 修改人：d407 <br/>
 * 修改时间：2013-6-4 下午1:18:02 <br/>
 * 修改备注： <br/>
 * 
 * @version <br/>
 * 
 */
package com.raising.system.modules.log.inf.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import org.apache.commons.lang.StringUtils;

import com.raising.system.modules.log.vo.LogVo;

/**
 * @author d407
 */
@SuppressWarnings("deprecation")
public class ILogSqlProvider {
	/**
	 * 插入一条日志
	 * 
	 * @param logVo
	 * @return
	 */
	public static String insertOneLog(LogVo logVo) {
		StringBuffer sb = null;
		sb=new StringBuffer();
		sb.append("INSERT /*+ APPEND */ INTO TB_SYS_LOGS");
		sb.append(" (LOGINNAME, REALNAME, DT, IP, MAC, CONTENT, USERTYPE, LOGTYPE, MODULENAME)");
		sb.append(" VALUES(");
		sb.append("#{loginname,javaType=string,jdbcType=VARCHAR},");
		sb.append("#{realname,javaType=string,jdbcType=VARCHAR},");
		sb.append("#{dt,javaType=long,jdbcType=NUMERIC},");
		sb.append("#{ip,javaType=string,jdbcType=VARCHAR},");
		sb.append("#{mac,javaType=string,jdbcType=VARCHAR},");
		sb.append("#{content,javaType=string,jdbcType=VARCHAR},");
		sb.append("#{usertype,javaType=string,jdbcType=VARCHAR},");
		sb.append("#{logtype,javaType=string,jdbcType=VARCHAR},");
		sb.append("#{modulename,javaType=string,jdbcType=VARCHAR}");
		sb.append(")");
		return sb.toString();
	}

	private static void SELECTMANYLOGS_WHERE(LogVo logVo) {
		if (StringUtils.isNotBlank(logVo.getIp())) {
			WHERE("IP=#{ip,javaType=string,jdbcType=VARCHAR}");
		}
		if (StringUtils.isNotBlank(logVo.getLoginname())) {
			WHERE("LOGINNAME=#{loginname,javaType=string,jdbcType=VARCHAR}");
		}
		if (StringUtils.isNotBlank(logVo.getUsertype())) {
			WHERE("USERTYPE=#{usertype,javaType=string,jdbcType=VARCHAR}");
		}
		if (StringUtils.isNotBlank(logVo.getLogtype())) {
			WHERE("LOGTYPE=#{logtype,javaType=string,jdbcType=VARCHAR}");
		}
		if (logVo.getBegindate() != null) {
			WHERE("dt>=#{begindate,javaType=long,jdbcType=NUMERIC}");
		}
		if (logVo.getEnddate() != null) {
			WHERE("dt<=#{enddate,javaType=long,jdbcType=NUMERIC}");
		}
	}

	public static String selectManyLogsCount(LogVo logVo) {
		BEGIN();
		SELECT("COUNT(*)");
		FROM("TB_SYS_LOGS");
		SELECTMANYLOGS_WHERE(logVo);
		return SQL();
	}

	public static String selectManyLogs(LogVo logVo) {
		BEGIN();
		SELECT("LOGINNAME as \"loginname\"");
		SELECT("REALNAME as \"realname\"");
		SELECT("IP as \"ip\"");
		SELECT("MAC as \"mac\"");
		SELECT("DT as \"dt\"");
		SELECT("CONTENT as \"content\"");
		SELECT("USERTYPE as \"usertype\"");
		SELECT("LOGTYPE as \"logtype\"");
		SELECT("MODULENAME as \"modulename\"");
		FROM("TB_SYS_LOGS");
		SELECTMANYLOGS_WHERE(logVo);
		ORDER_BY("DT DESC");
	/*	if (logVo.getPageSize() != GV.notPage) {// 分页
			return Func.getLimitString(SQL());
		}*/
		return SQL();
	}
}
