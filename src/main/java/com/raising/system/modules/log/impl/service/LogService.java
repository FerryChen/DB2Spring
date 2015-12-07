package com.raising.system.modules.log.impl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.raising.framework.exception.ServiceException;
import com.raising.system.framework.dao.page.IPage;
import com.raising.system.framework.dao.page.impl.PageImpl;
import com.raising.system.modules.log.annotation.SysLog;
import com.raising.system.modules.log.inf.mapper.ILogMapper;
import com.raising.system.modules.log.inf.service.ILogService;
import com.raising.system.modules.log.vo.LogVo;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
/**
 * 
 * 项目名称：PM <br/>
 * 类名称：MerchantService <br/>
 * 类描述：商户管理模块Service实现 <br/>
 * 创建人：d407 <br/>
 * 创建时间：2012-11-11 上午10:51:26 <br/>
 * 修改人：d407 <br/>
 * 修改时间：2012-11-11 上午10:51:26 <br/>
 * 修改备注： <br/>
 * 
 * @version <br/>
 * 
 */
@DependsOn(value = "ILogMapper")
@Service(value = "Spring_Service_Log")
public class LogService implements ILogService {
	@Autowired
	private ILogMapper	logMapper;

	@Override
	public void notraninsertOneLog(LogVo logVo) throws ServiceException {
		try {
			// int r = logMapper.insertOneLog(logVo);
			System.out.println("____________________________________");
			System.out.println("____________________________________");
			System.out.println("____________________________________");
			System.out.println("____________________________________");
			System.out.println("记录日志:" + logVo.getContent());
			logMapper.insertOneLog(logVo);
		} catch (Exception e) {
			throw new ServiceException("新增日志的业务异常", e);
		}
	}

	@SysLog(value = "日志查询")
	@Override
	public IPage<LogVo> selectManyLogs(LogVo logVo) {
		IPage<LogVo> result = new PageImpl<LogVo>();
		try {
			int count = logMapper.selectManyLogsCount(logVo);
			result.setRecordSize(count);
			if (count < 1)
				return result;
			List<LogVo> r = logMapper.selectManyLogs(logVo);
			result.setPageNo(logVo.getPageNo());
			result.setPageSize(logVo.getPageSize());
			result.setResults(r);
		} catch (Exception e) {
		}
		return result;
	}
}
