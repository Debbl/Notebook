# Spring 与 Web

> 06_Spring

- ## 添加Maven依赖

```xml
  <dependencies>
    <!--单元测试-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
    <!--spring核心ioc-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
    <!--做spring事务用到的-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
    <!--mybatis依赖-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.1</version>
    </dependency>
    <!--mybatis和spring集成的依赖-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.3.1</version>
    </dependency>
    <!--mysql驱动-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.9</version>
    </dependency>
    <!--阿里公司的数据库连接池-->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.1.12</version>
    </dependency>
    <!-- servlet依赖 -->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>
    <!-- jsp依赖 -->
    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>jsp-api</artifactId>
      <version>2.2.1-b03</version>
      <scope>provided</scope>
    </dependency>

    <!--为了使用监听器对象，加入依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
  </dependencies>

  <build>
    <!--目的是把src/main/java目录中的xml文件包含到输出结果中。输出到classes目录中-->
    <resources>
      <resource>
        <directory>src/main/java</directory><!--所在的目录-->
        <includes><!--包括目录下的.properties,.xml 文件都会扫描到-->
          <include>**/*.properties</include>
          <include>**/*.xml</include>
        </includes>
        <filtering>false</filtering>
      </resource>
    </resources>
    <!--指定jdk的版本-->
    <plugins>
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
```

- ## 添加index页面

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>注册学生</p>
    <form action="reg" method="post">
        <table>
            <tr>
                <td>id</td>
                <td><input type="text" name="id" /></td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td><input type="text" name="name" /></td>
            </tr>
            <tr>
                <td>email：</td>
                <td><input type="text" name="email" /></td>
            </tr>
            <tr>
                <td>年龄：</td>
                <td><input type="text" name="age" /></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="注册学生"/></td>
            </tr>
        </table>
    </form>
</body>
</html>

```

- ## 定义RegisterServlet

```java
package run.aiwan.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import run.aiwan.domain.Student;
import run.aiwan.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String strId = req.getParameter("id");
        String strName = req.getParameter("name");
        String strEmail = req.getParameter("email");
        String strAge = req.getParameter("age");

        // Spring容器创建对象
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        System.out.println("容器的信息" + ac);
        StudentService service = (StudentService) ac.getBean("studentService");

        Student student = new Student();
        student.setId(Integer.valueOf(strId));
        student.setName(String.valueOf(strName));
        student.setEmail(String.valueOf(strEmail));
        student.setAge(Integer.valueOf(strAge));

        service.addStudent(student);

        // 处理结果
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}

```

- ## 定义result.jsp页面

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
result.jsp 注册成功
</body>
</html>
```

- ## 总结

这样做是有问题的，每次刷新页面都要全部重新创建Spring容器对象



- ## 解决方案 使用Spring的监听器ContextLoaderListener

- ## 添加Maven依赖

```xml
<dependency> 
    <groupId>org.springframework</groupId> 
    <artifactId>spring-web</artifactId> 
    <version>5.2.5.RELEASE</version>
</dependency>
```

- ## 注册监听器ContextLoaderListenter和配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

  <servlet>
    <servlet-name>RegServlet</servlet-name>
    <servlet-class>run.aiwan.controller.RegisterServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>RegServlet</servlet-name>
    <url-pattern>/reg</url-pattern>
  </servlet-mapping>

  <!--监听器读取配置文件 默认applicationContext.xml-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring.xml</param-value>
  </context-param>
  <!--监听器-->
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
</web-app>
```

- ## 获取Spring容器对象

  - 第一种方式

  ```java
  package run.aiwan.controller;
  
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  import org.springframework.web.context.WebApplicationContext;
  import run.aiwan.domain.Student;
  import run.aiwan.service.StudentService;
  
  import javax.servlet.ServletException;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import java.io.IOException;
  
  public class RegisterServlet extends HttpServlet {
      @Override
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  
          String strId = req.getParameter("id");
          String strName = req.getParameter("name");
          String strEmail = req.getParameter("email");
          String strAge = req.getParameter("age");
  
          // Spring容器创建对象
          // String config = "applicationContext.xml";
          // ApplicationContext ac = new ClassPathXmlApplicationContext(config);
          // System.out.println("容器的信息" + ac);
          // StudentService service = (StudentService) ac.getBean("studentService");
  
          // 监听器第一种方式
          WebApplicationContext ac = null;
          String key = WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
          Object attr = getServletContext().getAttribute(key);
          if (attr != null) {
              ac = (WebApplicationContext) attr;
          }
          System.out.println("容器的信息" + ac);
          StudentService service = (StudentService) ac.getBean("studentService");
  
  
          Student student = new Student();
          student.setId(Integer.valueOf(strId));
          student.setName(String.valueOf(strName));
          student.setEmail(String.valueOf(strEmail));
          student.setAge(Integer.valueOf(strAge));
  
          service.addStudent(student);
  
          // 处理结果
          req.getRequestDispatcher("/result.jsp").forward(req, resp);
      }
  
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          super.doGet(req, resp);
      }
  }
  
  ```

  - 第二种

  ```java
  package run.aiwan.controller;
  
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  import org.springframework.web.context.WebApplicationContext;
  import org.springframework.web.context.support.WebApplicationContextUtils;
  import run.aiwan.domain.Student;
  import run.aiwan.service.StudentService;
  
  import javax.servlet.ServletException;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import java.io.IOException;
  
  public class RegisterServlet extends HttpServlet {
      @Override
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  
          String strId = req.getParameter("id");
          String strName = req.getParameter("name");
          String strEmail = req.getParameter("email");
          String strAge = req.getParameter("age");
  
          // Spring容器创建对象
          // String config = "applicationContext.xml";
          // ApplicationContext ac = new ClassPathXmlApplicationContext(config);
          // System.out.println("容器的信息" + ac);
          // StudentService service = (StudentService) ac.getBean("studentService");
  
          // 监听器第一种方式
          //WebApplicationContext ac = null;
          //String key = WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
          //Object attr = getServletContext().getAttribute(key);
          //if (attr != null) {
          //    ac = (WebApplicationContext) attr;
          //}
          //System.out.println("容器的信息" + ac);
          //StudentService service = (StudentService) ac.getBean("studentService");
  
          // 监听器第二种方式
          WebApplicationContext ac = null;
          ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
          StudentService service = (StudentService) ac.getBean("studentService");
          System.out.println("容器信息" + ac);
  
  
          Student student = new Student();
          student.setId(Integer.valueOf(strId));
          student.setName(String.valueOf(strName));
          student.setEmail(String.valueOf(strEmail));
          student.setAge(Integer.valueOf(strAge));
  
          service.addStudent(student);
  
          // 处理结果
          req.getRequestDispatcher("/result.jsp").forward(req, resp);
      }
  
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          super.doGet(req, resp);
      }
  }
  
  ```

  

