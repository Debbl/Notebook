package run.aiwan.ba01;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Date;

@Aspect
public class MyAspect {
    @Before(value = "execution(* doSome(..))")
    public void myBefore(JoinPoint joinPoint) {
        System.out.println("方法的签名（定义） " + joinPoint.getSignature());
        System.out.println("方法的名称 " + joinPoint.getClass().getName());
        // 获取方法的实参
        Object[] args = joinPoint.getArgs();
        for (Object arg:args) System.out.println("参数 " + arg);
        System.out.println("前置通知，切面功能: 在目标方法执行之前输出执行时间" + new Date());
    }
}
