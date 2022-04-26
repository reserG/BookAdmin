package com.bookctrlcenter.config;

import com.bookctrlcenter.api.CommonResult;
import com.bookctrlcenter.api.ResultCode;
import com.bookctrlcenter.controller.LoginController;
import com.bookctrlcenter.redis.RedisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class LoginAOP {

    @Autowired
    RedisService redisService;
    @Autowired
    HttpServletRequest request;

    @Around("!execution(*   com.bookctrlcenter.controller.LoginController.login(..)) && execution(* com.bookctrlcenter.controller..*(..))")
    public Object loginVerify(ProceedingJoinPoint joinPoint) throws Throwable {

//        HttpServletRequest request = (HttpServletRequest) RequestContextHolder.getRequestAttributes();
        System.out.println("aop执行");
        System.out.println(request.getSession().getId());
        System.out.println("X-Token  "+request.getHeader("X-Token"));
        String token = request.getHeader("X-Token");
//        System.out.println(redisService!=null&&redisService.exists(token));
        System.out.println(redisService.exists(token));
//        System.out.println(redisService!=null );

        if (redisService!=null&&redisService.exists(token)){
            redisService.expire(token,LoginController.ALIVETIME);
        }else {
            //方法必须要有返回值 否则错误信息将会发送浏览器失败
            return CommonResult.failed(ResultCode.UNAUTHORIZED);
        }
        return joinPoint.proceed();
    }

}
