# aop 面向切面编程

# 1.概念

### 1.1 什么是aop

要给业务方法增加功能，在原有的业务方法代码不变的情况向，增加一些功能

### 1.2aop的作用

- 实现解耦合：业务功能和其他的非业务功能的解耦合
- 减少重复的代码
- 专注业务方法的实现，不用考虑其他的代码

### 1.3Aspect切面

给业务方法增加的功能，切面通常是非业务功能，例如事务功能，日志，权限管理

### 1.4JoinPoint连接点

业务方法，表示这个方法执行时，要增加切面的功能

### 1.5Pointcut切入点

是多个连接的集合，表示切面在这些方法执行时，要增加切面的功能

### 1.6Advicet通知，增强

表示切面功能的执行时间

## 2.实现aop的框架

- spring框架：本身实现了aop的功能，spring框架本身的一些功能，用的是自己的aop
- aspectj框架：可以注解，xml配置文件实现aop

## 3.aop框架的使用

### 3.1作用aop，切面的要素

- 切面的功能
- 切面的执行位置 - 切入点表达式（方法的定义）

- 切面的执行时间 - 使用注解表示切面代码的执行时间

### 3.2实现aop的基本步骤

- 加入一个aspectj的依赖
- 定义业务方法
- 定义切面类
- 在spring的配置文件加入对象的声明

### 3.3@Before：前置通知

> ba01

Maven依赖

```xml
    <!--spring依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
    <!--aspectj依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
```

在目标方法之前执行的

- 1.公共方法 public

* 2.方法没有返回值
* 3.方法名称自定义
* 4.方法可以有参数，也可以没有参数。

指定通知方法中的参数 ： JoinPoint

```java
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
```

### 3.4@AfterReturning后置通知

> ba02

在目标方法执行之后执行，能够获取到目标方法的返回结果

```java
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

```

### 3.5@Aroud环绕通知

> ba03

在目标方法的前后都可以增强功能，可以修改目标方法的执行结果，控制方法的执行

```java
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
```

### 3.6@AfterThrowing异常通知

> ba04

在目标方法抛出异常时执行的，通过Exception类型的参数获取异常信息

```java
@Aspect
public class MyAspect {
    @AfterThrowing(value = "execution(* *doSome(..))", throwing = "ex")
    public void myAfterThrowing(Exception ex) {
        System.out.println("发生异常 " + ex.getMessage());
    }
}
```

### 3.6@After最终通知

> ba05

总是会被执行

```java
@Aspect
public class MyAspect {
    @After(value = "execution(* *doSome(..))")
    public void myAfter() {
        System.out.println("执行了最终通知");
    }
}
```

### 3.7@Pointcut

> ba06

定义和管理切入点的注释

```java
@Aspect
public class MyAspect {
    @Pointcut(value = "execution(* *doSome(..))")
    private void myPointcut() {

    }

    @Before(value = "myPointcut()")
    public void myBefore(JoinPoint joinPoint) {
        System.out.println("方法的签名（定义） " + joinPoint.getSignature());
        System.out.println("方法的名称 " + joinPoint.getClass().getName());
        // 获取方法的实参
        Object[] args = joinPoint.getArgs();
        for (Object arg:args) System.out.println("参数 " + arg);
        System.out.println("前置通知，切面功能: 在目标方法执行之前输出执行时间" + new Date());
    }

    @After(value = "myPointcut()")
    public void myAfter() {
        System.out.println("执行了最终通知");
    }
}
```

### 3.8没有接口自动采用cglib代理

> ba07

```java
@Test
    public void test01() {
        String config = "ba07/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        SomeServiceImpl proxy = (SomeServiceImpl) ac.getBean("someService");
        proxy.doSome("foo", 21);
        System.out.println(proxy.getClass().getName());
    }
```

> 前置通知，切面功能: 在目标方法执行之前输出执行时间Mon Jan 25 21:53:08 CST 2021
> doSome
> 执行了最终通知
> run.aiwan.ba07.SomeServiceImpl$$EnhancerBySpringCGLIB$$215f4510

### 3.9有接口也可以使用cglib代理

> ba08

```xml
    <aop:aspectj-autoproxy proxy-target-class="true"/>
```

