package com.raising.system.modules.log.impl.service;

import com.raising.system.modules.log.inf.service.ILogService;
import com.raising.system.modules.log.vo.LogVo;

public class LogThread implements Runnable {
	private ILogService	logService;
	private LogVo		logvo;

	public LogThread() {
		super();
	}

	public LogThread(ILogService logService, LogVo logvo) {
		super();
		this.logService = logService;
		this.logvo = logvo;
	}

	public LogVo getLogvo() {
		return logvo;
	}

	public void setLogvo(LogVo logvo) {
		this.logvo = logvo;
	}

	public ILogService getLogService() {
		return logService;
	}

	public void setLogService(ILogService logService) {
		this.logService = logService;
	}

	@Override
	public void run() {
		try {
			if (null != getLogService())
				getLogService().notraninsertOneLog(getLogvo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
