package com.congzer.pms.controller;

import com.congzer.pms.domain.SysLog;
import com.congzer.pms.service.SysLogService;
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

@Component
@Aspect
public class LogAop {

    private Date visitTime; //访问时间
    private Class executionClass; //访问的类
    private Method executionMethod; //访问的方法

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    //前置通知，主要是获取访问时间，访问的类以及访问的方法
    @Before("execution( * com.congzer.pms.controller.*.*(..)) && !execution(* com.congzer.pms.controller.SysLogController.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date(); //设置访问时间
        //获取访问的类
        executionClass = jp.getTarget().getClass();
        //获取访问的方法
        String methodName = jp.getSignature().getName(); //获取访问的方法名
        Object[] args = jp.getArgs(); //获取访问的方法的参数
        if(args == null || args.length == 0){
            //方法无参
            executionMethod = executionClass.getMethod(methodName); //获取无参的方法
        }else {
            //有参数，遍历参数，获取参数所属的Class，装入Class[]中
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName,classArgs); //获取有参的方法
        }
    }

    //后置通知，在方法执行完后执行，获取访问时长，访问ip，，访问的url，
    @After("execution(* com.congzer.pms.controller.*.*(..)) && !execution(* com.congzer.pms.controller.SysLogController.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //访问时长
        long time = new Date().getTime()-visitTime.getTime();

        //获取url
        String url = "";
        if(executionClass != null && executionMethod != null && executionClass != LogAop.class){
            //获取类上的注解RequestMapping("/user")
            RequestMapping classAnnotation = (RequestMapping)executionClass.getAnnotation(RequestMapping.class);
            if(classAnnotation != null){
                //类上RequestMapping注解不为空
                String[] classValues = classAnnotation.value();//获取RequestMapping注解的值
                //获取方法上的注解RequestMapping("/findAll.do")
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if(methodAnnotation != null){
                    //方法上注解不为空
                    String[] methodValue = methodAnnotation.value();
                    url = classValues[0]+methodValue[0];
                }
            }
            //获取访问者ip
            String ip = request.getRemoteAddr();

            //获取操作者的用户
            SecurityContext context = SecurityContextHolder.getContext();//获取上下文
            User user = (User) context.getAuthentication().getPrincipal();//从上下文中获取操作者对象
            String username = user.getUsername();//获取操作者用户名

            //将获取的日志信息存入日志类中
            SysLog sysLog = new SysLog();
            sysLog.setExecutionTime(time);
            sysLog.setIp(ip);
            sysLog.setMethod("[类名] "+executionClass.getName()+"[方法名 ]"+executionMethod.getName());
            sysLog.setUrl(url);
            sysLog.setUsername(username);
            sysLog.setVisitTime(visitTime);

            //调用serivce完成存入数据库的操作
            sysLogService.save(sysLog);
        }

    }
}
