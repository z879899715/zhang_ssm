package com.zhang.controller;

import com.zhang.domain.SysLog;
import com.zhang.sevice.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

//声明这是一个切面类 用于处理日志
@Component
@Aspect
public class LogAop {

	@Autowired
	private HttpServletRequest request;//用于获取ip地址

	@Autowired
	private ISysLogService sysLogService;//用于存储到数据库

	private Date visitTime;//访问开始时间
	private Class visitClass;//访问的类
	private Method visitMethod;//访问的方法
	//设置一个前置通知 主要用于获取访问时间 ,访问的类和方法
	@Before("execution(* com.zhang.controller.*.*(..))")
	public void dobefore(JoinPoint jp) throws NoSuchMethodException {
		visitTime = new Date();//访问时间
		//获取到访问的类
		visitClass = jp.getTarget().getClass();
		//获取到访问的方法
		String methodName = jp.getSignature().getName();//这是得到方法的名字
		Object[] args = jp.getArgs();//获取到方法中的参数列表
		//进行判断
		if(args == null || args.length == 0){
			visitMethod = visitClass.getMethod(methodName);
		}else{
			Class[] argClass = new Class[args.length];//因为需要参数的class
			//进行遍历
			for (int i = 0; i < args.length; i++) {
				if(args[i] == null) {
					args[i] = 1;//如果参数为null,那么一定是分页的原因
				}
				argClass[i] = args[i].getClass();
			}
			//获取方法对象
			visitMethod = visitClass.getMethod(methodName,argClass);
		}

	}


	//后置通知,用于获取 操作者(用户名),请求的ip地址,操作时间,url
	@After("execution(* com.zhang.controller.*.*(..))")
	public void doAfter(JoinPoint jp){
		long executionTime = new Date().getTime() - visitTime.getTime();//获取到操作时间
		String ip = request.getRemoteAddr();//获取请求的ip地址
		//获取操作者username
		SecurityContext context = SecurityContextHolder.getContext();
		User user = (User) context.getAuthentication().getPrincipal();
		String username = user.getUsername();
		//获取url

		if(visitClass != null && visitClass != LogAop.class && visitClass!= SysLogController.class && visitMethod != null){
			RequestMapping annotation = (RequestMapping) visitClass.getAnnotation(RequestMapping.class);
			System.out.println(visitMethod.getName());

			if(annotation != null ){
				RequestMapping Mannotation = visitMethod.getAnnotation(RequestMapping.class);
				if(Mannotation != null){

					String methodNameP = annotation.value()[0];

					String methodNameS = Mannotation.value()[0];
					String methodName = methodNameP+methodNameS;
					String url = "[类名]"+visitClass.getName()+"[方法名]"+ visitMethod.getName();//获取url
					SysLog sysLog = new SysLog();//创建一个日志对象
					//开始赋值
					sysLog.setIp(ip);
					sysLog.setVisitTime(visitTime);
					sysLog.setExecutionTime(executionTime);
					sysLog.setMethod(methodName);
					sysLog.setUrl(url);
					sysLog.setUsername(username);
					sysLogService.save(sysLog);
				}

			}

		}





	}


}
