# 解读\<url-pattern>

# 1. *.do

在没有特殊要求的情况下，SpringMVC 的中央调度器 DispatcherServlet 的\<url-pattern/>
常使用后辍匹配方式，如写为*.do 或者 *.action, *.mvc 等。

## 2. /

可以写为/，因为 DispatcherServlet 会将向静态资源的获取请求，例如.css、.js、.jpg、.png等资源的获取请求，当作是一个普通的 Controller 请求。中央调度器会调用处理器映射器为 其查找相应的处理器。当然也是找不到的，所以在这种情况下，所有的静态资源获取请求也 均会报 404 错误。

---

会拦截所有的请求，发送给DispatcherServlet，因为静态资源不是Controller对象，所以会发生请求静态资源404错误。

---

- maven依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>run.aiwan</groupId>
  <artifactId>04_url-pattert</artifactId>
  <version>1.0</version>
  <packaging>war</packaging>

  <name>04_url-pattert Maven Webapp</name>
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

---

解决方法

- 使用\<mvc:default-servlet-handler />

声 明 了\<mvc:default-servlet-handler /> 后， springmvc 框 架 会 在 容 器 中 创 建DefaultServletHttpRequestHandler 处理器对象。它会像一个检查员，对进入 DispatcherServlet 的 URL 进行筛查，如果发现是静态资源的请求，就将该请求转由 Web 应用服务器默认的 Servlet 处理。一般的服务器都有默认的 Servlet。在 Tomcat 中，有一个专门用于处理静态资源访问的 Servlet 名叫 DefaultServlet。其\<servlet-name/>为 default。可以处理各种静态资源访问请求。该 Servlet 注册在 Tomcat 服务 器的web.xml 中。在 Tomcat 安装目录/conf/web.xml。

> springmvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="run.aiwan.controller"/>

    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/view/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    <!--第一种处理静态资源的方式：
    需要在springmvc配置文件加入 <mvc:default-servlet-handler>
    原理是： 加入这个标签后，框架会创健控制器对象DefaultServletHttpRequestHandler（类似我们自己创建的MyController）.
    DefaultServletHttpRequestHandler这个对象可以把接收的请求转发给 tomcat的default这个servlet。
    -->
    <mvc:default-servlet-handler />

    <!-- default-servlet-handler 和 @RequestMapping注解 有冲突， 需要加入annotation-driven 解决问题-->
    <mvc:annotation-driven />
</beans>
```

- 使用\<mvc:resources />

在 Spring3.0 版本后，Spring 定义了专门用于处理静态资源访问请求的处理器ResourceHttpRequestHandler。并且添加了\<mvc:resources/>标签，专门用于解决静态资源无法访问问题。需要在 springmvc 配置文件中添加如下形式的配置：

> springmvc2.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="run.aiwan.controller"/>

    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/view/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!--第二种处理静态资源的方式
        mvc:resources 加入后框架会创建 ResourceHttpRequestHandler这个处理器对象。
        让这个对象处理静态资源的访问，不依赖tomcat服务器。
        mapping:访问静态资源的uri地址， 使用通配符 **
        location：静态资源在你的项目中的目录位置。

        images/**:表示 images/p1.jpg  , images/user/logo.gif , images/order/history/list.png
    -->
    <mvc:resources mapping="/images/**" location="/images/" />

    <!--mvc:resources和@RequestMapping有一定的冲突需要加上mvc:annotation-driven-->
    <mvc:annotation-driven />
</beans>
```

