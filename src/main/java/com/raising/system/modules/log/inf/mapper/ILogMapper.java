package com.raising.system.modules.log.inf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

import com.raising.db.DataSource;
import com.raising.system.framework.mybatis.IBaseMapper;
import com.raising.system.modules.log.inf.dao.ILogSqlProvider;
import com.raising.system.modules.log.vo.LogVo;

public interface ILogMapper extends IBaseMapper {
	/**
	 * 插入一条日志
	 * 
	 * @param logVo
	 * @return
	 */
	@InsertProvider(type = ILogSqlProvider.class, method = "insertOneLog", staticMethod = true)
	@Options(useCache = false)
	@ResultType(Integer.class)
	@DataSource("master")
	public int insertOneLog(LogVo logVo);

	/**
	 * 根据条件搜索日志Count
	 * 
	 * @param logVo
	 * @return
	 */
	@SelectProvider(type = ILogSqlProvider.class, method = "selectManyLogsCount", staticMethod = true)
	@ResultType(Integer.class)
	@DataSource("slave")
	public int selectManyLogsCount(LogVo logVo);

	/**
	 * 根据条件搜索日志
	 * 
	 * @param logVo
	 * @return
	 */
	@SelectProvider(type = ILogSqlProvider.class, method = "selectManyLogs", staticMethod = true)
	@ResultType(LogVo.class)
	@DataSource("slave")
	public List<LogVo> selectManyLogs(LogVo logVo);

}
