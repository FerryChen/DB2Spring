/**
 * 文件名：BaseController.java<br>
 * 版权：Copyright 2010-2014 raising Tech. Co. Ltd. All Rights Reserved.<br>
 * 描述：<描述><br>
 * 修改人：d407<br>
 * 修改时间：2011-5-31 下午04:14:40<br>
 * 跟踪单号: <跟踪单号><br>
 * 修改单号: <修改单号><br>
 * 修改内容：<修改内容><br>
 */
package com.raising.system.modules;

import java.io.OutputStreamWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.subject.Subject;

import com.raising.system.modules.log.inf.service.ILogService;
import com.raising.system.modules.log.vo.LogVo;

/**
 * Base控制器
 * 
 * @author d407
 * */
public class BaseController {
	@Resource(name = "Spring_Service_Log")
	private ILogService	logService;

	public ILogService getLogService() {
		return logService;
	}

	public void setLogService(ILogService logService) {
		this.logService = logService;
	}

	protected void postlog(HttpServletRequest request, LogVo logvo) {
	/*	if (null != this.getCurrUser()) {
			UserType usertype = this.getCurrUser().getUserType();
			logvo.setUsertype(String.valueOf(usertype.ordinal()));
		}
		String ipaddr = IPConfig.getIpAddr(request);
		logvo.setIp(ipaddr);
		logvo.setDt(Long.valueOf(DateUtil.format(new Date(), "yyyyMMddHHmmss")));
		LogThread logthread = new LogThread(logService, logvo);
		Thread thread = new Thread(logthread);
		thread.start();*/
	}

	protected void goback(HttpServletResponse response, String message) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
			try {
				StringBuffer sb = new StringBuffer();
				sb.append("<script>");
				if (StringUtils.isNotBlank(message)) {
					sb.append("alert(" + message + ")");
				}
				osw.write(sb.append("history.back();</script>").toString());
			} catch (Exception e) {
			} finally {
				if (osw != null) {
					osw.flush();
					osw.close();
				}
			}
		} catch (Exception e1) {
		}
	}
 

	/**
	 * 判断权限
	 * 
	 * @param permission
	 */
	protected void checkPermission(String permission) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.checkPermission(permission);
	}

	/**
	 * 判断权限
	 * 
	 * @param permissions
	 */
	protected void checkPermissions(String[] permissions) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.checkPermissions(permissions);
	}

	/**
	 * 判断权限
	 * 
	 * @param permissions
	 */
	protected void checkPermissions(String[] permissions, Logical logical) {
		Subject currentUser = SecurityUtils.getSubject();
		switch (logical) {
		case AND:
			currentUser.checkPermissions(permissions);
			break;
		case OR:
			for (String string : permissions) {
				if (currentUser.isPermitted(string))
					return;
			}
			currentUser.checkPermission(permissions[0]);
			break;
		default:
			break;
		}
	}
}
