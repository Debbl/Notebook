# 使用 AspectJ 的 AOP 配置管理事务

使用 XML 配置事务代理的方式的不足是，每个目标类都需要配置事务代理。当目标类较多，配置文件会变得非常臃肿。 使用 XML 配置顾问方式可以自动为每个符合切入点表达式的类生成事务代理。其用法很简单，只需将前面代码中关于事务代理的配置删除，再替换为如下内容即可。

> 在07_Spring上修改

- 加上aspectj依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.bjpowernode</groupId>
  <artifactId>ch10-spring-trans-aspectj</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <dependencies>
    <!--单元测试-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
    <!--aspectj依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>5.2.5.RELEASE</version>
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
</project>

```

- 在容器中添加事务管理器

```xml
    <!--spring的事务处理-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dateSource" />
    </bean>
```

- 事务通知

为事务通知设置相关属性。用于指定要将事务以什么方式织入给哪些方法。 例如，应用到 buy 方法上的事务要求是必须的，且当 buy 方法发生异常后要回滚业务

```xml
    <!--事务通知-->
    <tx:advice id="myAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="buy" propagation="REQUIRED" isolation="DEFAULT"
            rollback-for="java.lang.NullPointerException, run.aiwan.exce.NotEnoughException"/>
            <!--可以使用通配符，匹配多个方法-->
            <tx:method name="add*" />
        </tx:attributes>
    </tx:advice>
```

- 配置增强器

指定将配置好的事务通知，植入给谁

```xml
    <!--配置增强器-->
    <aop:config>
        <aop:pointcut id="servicePt" expression="execution(* *..service..*.*(..))" />
        <aop:advisor advice-ref="myAdvice" pointcut-ref="servicePt" />
    </aop:config>
```

- 完整代码

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--引入jdbc.properties文件-->
    <context:property-placeholder location="classpath:jdbc.properties" />

    <!--配置数据源连接数据库-->
    <bean id="dateSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="clone">
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.passwd}" />
        <property name="maxActive" value="${jdbc.max}" />
    </bean>

    <!--创建SqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dateSource" />
        <property name="configLocation" value="mybatis.xml" />
    </bean>

    <!--Spring帮助调用dao包下的getMapper方法-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
        <property name="basePackage" value="run.aiwan.dao" />
    </bean>

    <!--Service-->
    <bean id="buyGoodsService" class="run.aiwan.service.impl.BuyGoodsServiceImpl">
        <property name="goodsDao" ref="goodsDao" />
        <property name="saleDao" ref="saleDao" />
    </bean>

    <!--spring的事务处理-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dateSource" />
    </bean>

    <!--事务通知-->
    <tx:advice id="myAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="buy" propagation="REQUIRED" isolation="DEFAULT"
            rollback-for="java.lang.NullPointerException, run.aiwan.exce.NotEnoughException"/>
            <!--可以使用通配符，匹配多个方法-->
            <tx:method name="add*" />
        </tx:attributes>
    </tx:advice>

    <!--配置增强器-->
    <aop:config>
        <aop:pointcut id="servicePt" expression="execution(* *..service..*.*(..))" />
        <aop:advisor advice-ref="myAdvice" pointcut-ref="servicePt" />
    </aop:config>
</beans>
```

