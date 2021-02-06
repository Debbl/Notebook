# 1. 处理器方法的返回值

## 1.1 返回 ModelAndView

若处理器方法处理完后，需要跳转到其它资源，且又要在跳转的资源间传递数据，此时
处理器方法返回 ModelAndView 比较好。当然，若要返回 ModelAndView，则处理器方法中 需要定义ModelAndView对象。

在使用时，若该处理器方法只是进行跳转而不传递数据，或只是传递数据而并不向任何
资源跳转（如对页面的 Ajax 异步响应），此时若返回 ModelAndView，则将总是有一部分多 余：要么Model 多余，要么 View 多余。即此时返回ModelAndView 将不合适。

## 1.2 返回 String

处理器方法返回的字符串可以指定逻辑视图名，通过视图解析器解析可以将其转换物理的视图地址，最终还是会跳转到一个视图

```java
@Controller
public class MyController {
    @RequestMapping("some.do")
    public String doSome() {
        return "show";
    }
}
```

## 1.3  返回 void

- 处理 json 数据，加入 Jackson 依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>run.aiwan</groupId>
  <artifactId>03_ReturnValue</artifactId>
  <version>1.0</version>
  <packaging>war</packaging>

  <name>03_ReturnValue Maven Webapp</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
    <!--servlet依赖-->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>
    <!--springmvc依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
    <!--Jackson依赖-->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>2.9.0</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.9.0</version>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <!-- 编码和编译和JDK版本 -->
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>

```

- 引入 jQuery 库

> js

- index.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
        <script type="text/javascript">
            $(function () {
                $("button").click(function () {
                    $.ajax({
                        url: "myAjax.do",
                        data: {
                            name: "foo",
                            age: "21"
                        },
                        type: "post",
                        dataType: "json",
                        success: function (resp) {
                            alert("resp==========" + resp.name + "  " + resp.age);
                        }

                    })
                });
            });
        </script>
        <title>Title</title>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    </head>

    <body>
        <button>发起Ajax请求</button>
    </body>

</html>
```

- 定义 Student  类、

- MyController

```java
@RequestMapping("myAjax.do")
    public void doMyAjax(Student student, HttpServletResponse response) throws IOException {
        student.setName("bar");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(student);

        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();

        pw.println(json);
        pw.flush();
        pw.close();
    }
```

## 1.4 返回对象 Object

处理器方法也可以返回 Object 对象。这个 Object 可以是 Integer，String，自定义对象，Map，List 等。但返回的对象不是作为逻辑视图出现的，而是作为直接在页面显示的数据出现的。返回对象，需要使用@ResponseBody 注解，将转换后的 JSON 数据放入到响应体中。

- 导入 Jackson 依赖

- 声明注解驱动

将 Object 数据转化为 JSON 数据，需要由消息转换器 HttpMessageConverter 完成。而转
换器的开启，需要由]\<mvc:annotation-driven/>来完成。

SpringMVC 使用消息转换器实现请求数据和对象，处理器方法返回对象和响应输出之间
的自动转换。

当 Spring 容器进行初始化过程中，在\<mvc:annotation-driven/>处创建注解驱动时，默认
创建了七个 HttpMessageConverter 对象。也就是说，我们注册\<mvc:annotation-driven/>，就 是为了让容器为我们创建 HttpMessageConverter 对象。

> springmvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="run.aiwan.controller" />

    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/view/" />
        <property name="suffix" value=".jsp" />
    </bean>

    <!--声明注解驱动-->
    <mvc:annotation-driven />
</beans>
```

- index.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
        <script type="text/javascript">
            $(function () {
                $("button").click(function () {
                    $.ajax({
                        url: "myAjax2.do",
                        data: {
                            name: "foo",
                            age: "21"
                        },
                        type: "post",
                        dataType: "json",
                        success: function (resp) {
                            alert("resp==========" + resp.name + "  " + resp.age);
                        }

                    })
                });
            });
        </script>
        <title>Title</title>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    </head>

    <body>
        <button>发起Ajax请求</button>
    </body>

</html>
```

- MyController

```java
    @RequestMapping("myAjax2.do")
    @ResponseBody
    public Student doMyAjax2(Student student) {
        student.setName("bar");
        return student;
    }
```

- 返回 List 集合

```java
    @RequestMapping(value = "/returnStudentJsonArray.do")
    @ResponseBody
    public List<Student> doStudentJsonObjectArray(String name, Integer age) {

        List<Student> list = new ArrayList<>();
        //调用service，获取请求结果数据 ， Student对象表示结果数据
        Student student = new Student();
        student.setName("李四同学");
        student.setAge(20);
        list.add(student);

        student = new Student();
        student.setName("张三");
        student.setAge(28);
        list.add(student);


        return list;

    }
```

- 返回字符串对象

这里返回的是数据，不是视图，注意在 @RequestMapping 设置字符集

```java
    @RequestMapping(value = "/returnStringData.do",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String doStringData(String name,Integer age){
        return "Hello SpringMVC 返回对象，表示数据";
    }
```

