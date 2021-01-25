package run.aiwan.ba03;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyAspect {
    @Around(value = "execution(* *doOther(..))")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("目标方法之前");
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg:args) {
            System.out.println("参数 " + arg);
        }
        // 执行目标方法
        proceedingJoinPoint.proceed();
        System.out.println("目标方法之后");
        // 可以改变方法的执行结果
        return "bar";
    }
}
