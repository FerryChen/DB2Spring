package com.raising.system.modules.log.inf.service;

import com.raising.framework.exception.ServiceException;
import com.raising.system.framework.dao.page.IPage;
import com.raising.system.modules.log.vo.LogVo;

/**
 * 
 * 
 * 项目名称：PM <br/>
 * 类名称：ILogService <br/>
 * 类描述：日志管理模块Service接口 <br/>
 * 创建人：d407 <br/>
 * 创建时间：2012-11-16 下午9:38:51 <br/>
 * 修改人：d407 <br/>
 * 修改时间：2012-11-16 下午9:38:51 <br/>
 * 修改备注： <br/>
 * 
 * @version <br/>
 * 
 */
public interface ILogService {

	public void notraninsertOneLog(LogVo logVo) throws ServiceException;

	public IPage<LogVo> selectManyLogs(LogVo logVo);

}
