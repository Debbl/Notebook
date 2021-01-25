package run.aiwan.ba04;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyAspect {
    @AfterThrowing(value = "execution(* *doSome(..))", throwing = "ex")
    public void myAfterThrowing(Exception ex) {
        System.out.println("发生异常 " + ex.getMessage());
    }
}
