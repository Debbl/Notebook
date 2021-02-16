# Spring Boot 集成 MyBatis

> MySQL 5.7.26

## 1. 添加 Maven 依赖

> MyBatis 整合 SpringBoot 的起步依赖
>
> MySQL 的驱动依赖
>
> 指定Mapper文件夹

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.2</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>run.aiwan</groupId>
    <artifactId>05-springboot-mybatis</artifactId>
    <version>0.0.1</version>
    <name>05-springboot-mybatis</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <!--SpringBoot框架web项目起步依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--MySQL驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <!--<version>5.1.9</version>-->
        </dependency>

        <!--MyBatis整合SpringBoot框架的起步依赖-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.0.0</version>
        </dependency>
    </dependencies>

    <build>

        <!--手动指定Mapper文件夹为resources-->
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

## 2. 连接数据库配置文件

```properties
# 数据库连接信息
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/springbd?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8
spring.datasource.username=root
spring.datasource.password=111111
```

## 3. MyBatis 组件扫描器

- 第一种方式 @Mapper

```java
package run.aiwan.dao;

import org.apache.ibatis.annotations.Mapper;
import run.aiwan.domain.Student;

import java.util.List;

@Mapper
public interface StudentDao {
    // 查询学生
    List<Student> queryStudents();
}
```

- 第二种方式 @MapperScan

```java
package run.aiwan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// Mybatis提供的注解：扫描数据持久层的 mapper映谢配置文件,DAO接口上就不用加@Mapper
// basePackages 通常指定到数据持久层包即可

// @MapperScan("run.aiwan.dao")
@MapperScan(basePackages = "run.aiwan.dao")

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

## 4. 将接口和映射文件分开

```properties
# 数据库连接信息
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/springbd?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8
spring.datasource.username=root
spring.datasource.password=111111

# 将接口和配置文件分开 指定mapper文件位置
mybatis.mapper-locations=classpath:mapper/*.xml
```