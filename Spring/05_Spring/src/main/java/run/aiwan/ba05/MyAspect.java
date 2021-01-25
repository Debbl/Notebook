package run.aiwan.ba05;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyAspect {
    @After(value = "execution(* *doSome(..))")
    public void myAfter() {
        System.out.println("执行了最终通知");
    }
}
