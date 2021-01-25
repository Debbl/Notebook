package run.aiwan.ba02;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyAspect {

    @AfterReturning(value = "execution(* *doOther(..))", returning = "res")
    public void myAfterReturning(JoinPoint joinPoint, Object res) {
        System.out.println("后置通知，方法的定义 " + joinPoint.getSignature());
        // 方法的返回结果
        System.out.println("后置通知，在目标方法之后执行的 " + res);
        if ("foo".equals(res)) {
            System.out.println("===foo===");
        } else {

        }
        // 不会改变返回结果
        if (res != null) {
            res = "bar";
        }
    }
}
