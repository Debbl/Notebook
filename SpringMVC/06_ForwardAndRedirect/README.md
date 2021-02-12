# 请求转发和重定向

当处理器对请求处理完毕后，向其它资源进行跳转时，有两种跳转方式：请求转发与重定向。而根据所要跳转的资源类型，又可分为两类：跳转到页面与跳转到其它处理器。 **注意**，对于**请求转发的页面**，可以是WEB-INF中面，而**重定向的页面**，是不能为WEB-INF中页的。因为**重定向相当于用户再次发出一次请求**，而用户是不能直接访问 WEB-INF 中资源的。**这两个功能都不可以使用视图解析器，必须要写完整路径。**

## 1. 请求转发

## 2. 重定向

```java
package run.aiwan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @RequestMapping(value = "/doForward.do")
    public ModelAndView doSome() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "springMVC");
        mv.addObject("fun", "执行doSome方法");

        // 请求转发
        mv.setViewName("forward:/WEB-INF/view/show.jsp");
        return mv;
    }

    @RequestMapping(value = "doRedirect.do")
    public ModelAndView doWithRedirect(String name, Integer age) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("myname", name);
        mv.addObject("myage", age);

        // 重定向 不能访问/WEB-INF资源
        mv.setViewName("redirect:/hello.jsp");
        return mv;
    }
}

```

重定向获取参数，实际是从地址获取值

http://localhost:8080/06_ForwardAndRedirect/hello.jsp?myname=foo&myage=21

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>/WEB-INF/view/hello.jsp从request作用域获取数据</h3><br/>
    <h3>myname数据：${param.myname}</h3><br/>
    <h3>myage数据：${param.myage}</h3>
    <h3>取参数数据：<%=request.getParameter("myname")%></h3>
</body>
</html>
```

