package com.raising.system.modules.log.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.raising.system.framework.dao.page.IPage;
import com.raising.system.modules.BaseController;
import com.raising.system.modules.log.inf.service.ILogService;
import com.raising.system.modules.log.vo.LogVo;

/**
 * 日志控制器
 * 
 * @author d407
 * 
 */
@Controller()
public class LogController extends BaseController {
	@Resource(name = "Spring_Service_Log")
	private ILogService	logService;
	private final int	pageSize	= 10;

	 

	/**
	 * 进入角色添加页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "systemlogs.json", method = { RequestMethod.POST })
	public IPage<LogVo> systemlogslist(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer userpageSize, ModelMap model) {
		int tpageNo = 1, tpageSize = pageSize;
		if (pageNo != null && pageNo > 0)
			tpageNo = pageNo;
		if (userpageSize != null && userpageSize > 0)
			tpageSize = userpageSize;
		LogVo parame = new LogVo();
		parame.setPageSize(tpageSize);
		parame.setPageNo(tpageNo);
		String loginname = request.getParameter("loginname");
		String ip = request.getParameter("ip");
		String begindate = request.getParameter("begindate");
		String enddate = request.getParameter("enddate");
		if (StringUtils.isNotBlank(loginname)) {
			parame.setLoginname(loginname.trim());
		}
		if (StringUtils.isNotBlank(ip)) {
			parame.setIp(ip.trim());
		}
		if (StringUtils.isNotBlank(begindate) && StringUtils.isNumeric(begindate.trim().replace("-", ""))) {
			parame.setBegindate(Long.valueOf(begindate.trim().replace("-", "").concat("000000")));
		}
		if (StringUtils.isNotBlank(enddate) && StringUtils.isNumeric(enddate.trim().replace("-", ""))) {
			parame.setEnddate(Long.valueOf(enddate.trim().replace("-", "").concat("235959")));
		}
		IPage<LogVo> r = logService.selectManyLogs(parame);
		return r;
	}

}
