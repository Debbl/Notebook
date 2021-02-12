# 异常处理

## 1. 异常处理类

> run.aiwan.Exception

```java
package run.aiwan.exception;

// 用户异常
public class MyUserException extends Exception {
    public MyUserException() {
        super();
    }

    public MyUserException(String message) {
        super(message);
    }
}

```

```java
package run.aiwan.exception;

// 用户姓名有问题时，抛出的异常
public class NameException extends MyUserException {
    public NameException() {
        super();
    }

    public NameException(String message) {
        super(message);
    }
}

```

```java
package run.aiwan.exception;

// 用户年龄有问题是，抛出的异常
public class AgeException extends MyUserException {
    public AgeException() {
        super();
    }

    public AgeException(String message) {
        super(message);
    }
}

```

## 2. 修改 Controller 抛出异常

```java
package run.aiwan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import run.aiwan.exception.AgeException;
import run.aiwan.exception.MyUserException;
import run.aiwan.exception.NameException;

@Controller
public class MyController {

    @RequestMapping(value = "some.do")
    public ModelAndView doSome(String name, Integer age) throws MyUserException {

        ModelAndView mv = new ModelAndView();

        if (!"foo".equals(name)) {
            throw new NameException("姓名不正确！！！");
        }

        if (age == null || age > 80) {
            throw new AgeException("年龄比较大！！！");
        }

        mv.addObject("myName", name);
        mv.addObject("myAge", age);
        mv.setViewName("show");
        return mv;
    }
}

```

## 3. 相应结果页面

> index.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
<head>
    <title>Title</title>
    <base href="<%=basePath%>" />
</head>
<body>

     <p>处理异常的，全局异常处理</p>
    <form action="some.do" method="post">
        姓名：<input type="text" name="name"> <br/>
        年龄：<input type="text" name="age"> <br/>
        <input type="submit" value="提交请求">
    </form>
</body>
</html>

```

## 4. 定义全局异常处理类

- @ControllerAdvice 控制器增强，是给控制器对象增强功能的。使用@ControllerAdvice 修饰的类中可以使用@ExceptionHandler。

- @ExceptionHandler

当使用@RequestMapping 注解修饰的方法抛出异常时，会执行@ControllerAdvice 修饰的类中的异常处理方法。 @ControllerAdvice 是使用@Component 注解修饰的，可以\<context:component-scan> 扫描到@ControllerAdvice 所在的类路径(包名)，创建对象。

```java
package run.aiwan.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import run.aiwan.exception.AgeException;
import run.aiwan.exception.NameException;

@ControllerAdvice
public class GlobalExceptionHandler {
    //定义方法，处理发生的异常
    /*
        处理异常的方法和控制器方法的定义一样， 可以有多个参数，可以有ModelAndView,
        String, void,对象类型的返回值

        形参：Exception，表示Controller中抛出的异常对象。
        通过形参可以获取发生的异常信息。

        @ExceptionHandler(异常的class)：表示异常的类型，当发生此类型异常时，
        由当前方法处理
     */
    @ExceptionHandler(value = NameException.class)
    public ModelAndView doNameException(Exception exception) {
         //处理NameException的异常。
        /*
           异常发生处理逻辑：
           1.需要把异常记录下来， 记录到数据库，日志文件。
             记录日志发生的时间，哪个方法发生的，异常错误内容。
           2.发送通知，把异常的信息通过邮件，短信，微信发送给相关人员。
           3.给用户友好的提示。
         */
         ModelAndView mv = new ModelAndView();

         mv.addObject("msg", "姓名必须是foo,其他用户不能访问！！！");
         mv.addObject("ex", exception);
         mv.setViewName("nameError");

         return mv;
    }

    @ExceptionHandler(value = AgeException.class)
    public ModelAndView doAgeException(Exception exception) {
        //处理AgeException的异常。
        /*
           异常发生处理逻辑：
           1.需要把异常记录下来， 记录到数据库，日志文件。
             记录日志发生的时间，哪个方法发生的，异常错误内容。
           2.发送通知，把异常的信息通过邮件，短信，微信发送给相关人员。
           3.给用户友好的提示。
         */
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "你的年龄不能大于80！！！");
        mv.addObject("ex", exception);
        mv.setViewName("ageError");

        return mv;
    }

    // 处理其他异常， NameException, AgeException以外，不知类型的异常
    @ExceptionHandler
    public ModelAndView doOtherException(Exception exception) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "不知道异常！！！");
        mv.addObject("ex", exception);
        mv.setViewName("defaultError");

        return mv;
    }
}

```

## 5. 定义 Spring 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--声明组件扫描器，通过IOC会自动创建包下的被注解(@Controller)的对象-->
    <context:component-scan base-package="run.aiwan.controller" />

    <!--视图解析器，因为*.jsp一般放在被保护的目录/WEB-INF/*下，可以少些文件目录的名称，实质是字符串的拼接-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!--前缀-->
        <property name="prefix" value="/WEB-INF/view/" />
        <!--后缀-->
        <property name="suffix" value=".jsp" />
    </bean>

    <!--添加扫描器扫描handler包-->
    <context:component-scan base-package="run.aiwan.handler" />
    <mvc:annotation-driven />
</beans>
```

## 6. web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

  <servlet>
    <servlet-name>SpringMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springmvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>

  <servlet-mapping>
    <servlet-name>SpringMVC</servlet-name>
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>
</web-app>

```

