package com.raising.system.modules.log.vo;

import java.util.HashMap;
import java.util.Map;

import com.raising.system.modules.base.vo.BasePoJoVo;

/**
 * 日志的VO
 * 
 * @author <a href="mailto:zhangzhilin.r@gmail.com">d407</a>
 * 
 */
public class LogVo extends BasePoJoVo {
	private static Map<String, String>	logtypemap			= new HashMap<String, String>();
	static {
		logtypemap.put("0", "登录系统");
		logtypemap.put("1", "退出系统");
		logtypemap.put("2", "查询");
		logtypemap.put("3", "修改");
		logtypemap.put("4", "添加");
		logtypemap.put("5", "删除");
		logtypemap.put("5", "审核");
	}
	private static final long			serialVersionUID	= 1L;
	private String						loginname;												// VARCHAR2(40) not null,
	private String						realname;												// VARCHAR2(32),
	private long						dt;	
	@SuppressWarnings("unused")
	private String						dtstr;
	private String						ip;													// VARCHAR2(15),
	private String						mac;													// VARCHAR2(20),
	private String						content;												// VARCHAR2(2000)
	private String						usertype;												// VARCHAR2(2000)
	private String						logtype;												// VARCHAR2(2000)
	@SuppressWarnings("unused")
	private String						logtypeStr;											// VARCHAR2(2000)
	private String						modulename;											// VARCHAR2(2000)
	private Long						begindate;												// DATE default sysdate not null,
	private Long						enddate;												// DATE default sysdate not null,

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public long getDt() {
		return dt;
	}

	public void setDt(long dt) {
		this.dt = dt;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getLogtype() {
		return logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	@Override
	public String tablename() {
		return null;
	}

	@Override
	public String id() {
		return null;
	}

	public String getDtstr() {
		return "";
	}

	public void setDtstr(String dtstr) {
		this.dtstr = dtstr;
	}

	public Long getBegindate() {
		return begindate;
	}

	public void setBegindate(Long begindate) {
		this.begindate = begindate;
	}

	public Long getEnddate() {
		return enddate;
	}

	public void setEnddate(Long enddate) {
		this.enddate = enddate;
	}

	public String getLogtypeStr() {
		return logtypemap.get(this.logtype);
	}

	public void setLogtypeStr(String logtypeStr) {
		this.logtypeStr = logtypeStr;
	}

	 
}