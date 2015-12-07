package com.raising.system.modules.log.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//此注解不支持SpringMvc的controller，所以使用强配置的方式
/*@Aspect
 @Component*/
public class LogAspect {
	@Pointcut("@annotation(com.raising.system.modules.log.annotation.SysLog)")
	public void methodLogPointcut() {
		// @Pointcut("within(com.raising.system.modules..*)")
	}

	// 标注该方法体为后置通知，当目标方法执行成功后执行该方法体
	@Before(value = "methodLogPointcut()")
	public void addLogBefore(JoinPoint jp) {
		System.out.println("============================addLogBefore");
	}

	// 标注该方法体为后置通知，当目标方法执行成功后执行该方法体
	@After("methodLogPointcut()")
	public void addLogAfter(JoinPoint jp) {
		System.out.println("============================addLogSuccess");
		/*
		 * Object[] parames = jp.getArgs();// 获取目标方法体参数 String className = jp.getTarget().getClass().toString();//
		 * 获取目标类名 System.out.println("className = " + className); className =
		 * className.substring(className.indexOf("com")); String signature = jp.getSignature().toString();// 获取目标方法签名
		 * String methodName = signature.substring(signature.lastIndexOf(".") + 1, signature.indexOf("("));
		 */
	}

	// 标注该方法体为后置通知，当目标方法执行成功后执行该方法体
	@AfterReturning("methodLogPointcut()")
	public void addLogSuccess(JoinPoint jp) {
		System.out.println("============================addLogSuccess");
	}

	// 标注该方法体为异常通知，当目标方法出现异常时，执行该方法体
	@AfterThrowing(pointcut = "methodLogPointcut()", throwing = "ex")
	public void addLogError(JoinPoint jp, Exception ex) {
		System.out.println("============================addLogError" + ex.getMessage());
	}
}
