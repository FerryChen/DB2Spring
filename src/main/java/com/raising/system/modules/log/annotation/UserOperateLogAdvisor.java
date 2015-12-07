package com.raising.system.modules.log.annotation;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

import com.raising.framework.utils.res.Res;
import com.raising.system.modules.log.inf.service.ILogService;
import com.raising.system.modules.log.vo.LogVo;

@DependsOn(value = "Spring_Service_Log")
public class UserOperateLogAdvisor implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {
	private final org.apache.commons.logging.Log	log	= Res.getLog(UserOperateLogAdvisor.class);
	@Autowired
	private HttpServletRequest						request;

	@Resource(name = "Spring_Service_Log")
	private ILogService								logService;

	/**
	 * 数据库的日志添加的，后期计划使用消息队列提高性能。
	 * 
	 * @param logvo
	 */
	private void addlog(LogVo logvo) {
		// 后期可以考虑直接把日志放到消息队列，专门一个服务去处理日志。
	/*	Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getPrincipal();
		if (user == null)
			return;
		UserType usertype = user.getUserType();
		logvo.setUsertype(String.valueOf(usertype.ordinal()));
		logvo.setLoginname(user.getLoginName());
		String ipaddr = IPConfig.getIpAddr(request);
		logvo.setIp(ipaddr);
		logvo.setDt(Long.valueOf(DateUtil.format(new Date(), "yyyyMMddHHmmss")));
		LogThread logthread = new LogThread(logService, logvo);
		Thread thread = new Thread(logthread);
		thread.start();*/
	}

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		// 判断方法是否注解了UserOperateLog
		if (!arg0.isAnnotationPresent(SysLog.class))
			return;
		SysLog anno = arg0.getAnnotation(SysLog.class);
		if (anno == null)
			return;
		switch (anno.viewType()) {
		case CONSOLE:
			System.out.println("控制台");
			break;
		case LOG4J:
			System.out.println("LOG4J");
			log.info("这是一个Info输出日志");
			break;
		case DB:
			LogVo logvo = new LogVo();
			logvo.setContent(anno.value());
			addlog(logvo);
			System.out.println("保存到数据库");
			break;
		default:
			break;
		}
		/*
		 * HttpServletRequest r = (HttpServletRequest)arg1[0]; System.out.println("sessionid = " +
		 * r.getSession().getId());
		 */
		System.out.println("sessionid = " + request.getSession().getId());
		// System.out.println(arg1[0]);
		// String username = "未知";
		// for (Object object : arg1) {
		// 这里只提供一种获得操作人的方式，既从HttpSession中获取，但这要求方法参数中包含 HttpSession
		// if (object instanceof HttpSession) {
		// username = ((HttpSession) object).getAttribute("username") == null ? "未知" : (String) ((HttpSession)
		// object).getAttribute("username");
		// }
		// String template = anno.value();
		// final Map<String, String> map = new HashMap<String, String>();
		// map.put("date", MV.longdate.format(new Date()));
		// JsonResult jr = new JsonResult();
		// jr.setResultMessage("这是消息");
		// map.put("user", MV.longdate.format(new Date()));
		// // parse
		//
		// Expression el = new ExpressionImpl(template);
		// Object result21 = el.evaluate("obj", jr);
		// System.out.println("值 =" + result21);
		// System.out.println("arg2 = " + arg2);
	}

	// String defaultMessage = anno.value();
	// String methodName = arg2.getClass().getName() + "." + arg0.getName();
	// }

	@Override
	public void afterReturning(Object obj, Method method, Object[] aobj, Object obj1) throws Throwable {

	}

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
		log.error(ex.getMessage());
	}

	/*
	 * <!-- 定义用户操作日志切入点和通知器 --> <aop:config proxy-target-class="true"> <aop:pointcut id="operatePoint"
	 * expression="@annotation(com.UserOperateLog)" /> <aop:advisor pointcut-ref="operatePoint" id="logAdvisor"
	 * advice-ref="userOperateLogAdvisor" /> </aop:config>
	 * 
	 * <!-- 定义日志文件写入位置，需要在log4j.properties中加入名称为 useroperatorlog的日志配置--> <bean id="userOperateLogAdvisor"
	 * class="com.UserOperateLogAdvisor" p:loggerName="useroperatorlog"
	 * p:propertiesFilePath="classpath:messages/messages.properties"/>
	 */
}
