# 1. @Request Mapping 定义请求规则

## 指定模块名称

- 放在类上
- 放在类的方法上

```java
package run.aiwan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class MyController {
    @RequestMapping(value = "/some.do")
    public ModelAndView doSome() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "message");
        mv.addObject("fun", "fun方法");
        mv.setViewName("show");
        return mv;
    }
}

```

## 对请求提交方法的定义

```java
package run.aiwan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class MyController {
    @RequestMapping(value = "/some.do", method = RequestMethod.GET)
    public ModelAndView doSome() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "message");
        mv.addObject("fun", "fun方法");
        mv.setViewName("show");
        return mv;
    }
}

```

# 2. 处理器方法的参数

- HttpServletRequest
- HttpServletResponse
- HttpSession
- 请求中所携带的请求参数

> SpringMVC会自动给这些参数赋值

```java
package run.aiwan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class MyController {
    @RequestMapping(value = "/some.do", method = RequestMethod.GET)
    public ModelAndView doSome(HttpServletRequest request,
                               HttpServletResponse response,
                               HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "message");
        mv.addObject("fun", "fun方法");
		mv.setViewName("show");
        return mv;
    }
}

```

## 逐个参数接收

只要保证请求参数名与该请求处理方法的参数名相同即可。

> index.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<form action="user/other.do" method="post">
    姓名：<input type="text" name="name"> <br/>
    年龄：<input type="text" name="age"> <br/>
    <input type="submit" value="提交参数">
</form>
</body>
</html>
```

> /WEB-INF/view/show.jsp

```
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
name ${name}
age ${age}
</body>
</html>
```

声明过滤器，解决post请求乱码问题

> web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <servlet>
        <servlet-name>springMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springMVC</servlet-name>
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>

    <!--注册声明过滤器，解决post请求乱码的问题-->
    <filter>
        <filter-name>characterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <!--设置项目中使用的字符编码-->
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
        <!--强制请求对象（HttpServletRequest）使用encoding编码的值-->
        <init-param>
            <param-name>forceRequestEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
        <!--强制应答对象（HttpServletResponse）使用encoding编码的值-->
        <init-param>
            <param-name>forceResponseEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <!--
           /*:表示强制所有的请求先通过过滤器处理。
        -->
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

处理器类

```java
    @RequestMapping(value = "/other.do", method = RequestMethod.POST)
    public ModelAndView doOther(String name, Integer age) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", name);
        mv.addObject("age", age);
        mv.setViewName("show");
        return mv;
    }
```

## 校正请求参数名（自定义请求参数名）

@RequestParam(value = "rname")

> MyController

```java
    @RequestMapping(value = "/other.do", method = RequestMethod.POST)
    public ModelAndView doOther(@RequestParam(value = "rname") String name,
                                @RequestParam(value = "rage") Integer age) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", name);
        mv.addObject("age", age);
        mv.setViewName("show");
        return mv;
    }
```

## 对象参数接收

将处理器方法的参数定义为一个对象，只要保证请求参数名与这个对象的属性同名即可。

```java
    @RequestMapping(value = "/requestObject.do", method = RequestMethod.POST)
    public ModelAndView requestObject(Student student) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", student.getName());
        mv.addObject("age", student.getAge());
        mv.setViewName("show");
        return mv;
    }
```

# 