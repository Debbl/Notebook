# Spring Boot 事务支持

> 05_SpringBootMyBatis

Spring Boot 使用事务非常简单，底层依然采用的是 Spring 本身提供的事务管理

## 1. @EnableTransactionManagement

- 在入口类中使用注解 @EnableTransactionManagement 开启事务支持

- 在访问数据库的 Service 方法上添加注解 @Transactional 即可

```java
package run.aiwan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// 可以不加，默认是加的
//@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

```

## 2. @Transactional

```
package run.aiwan.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.aiwan.dao.StudentDao;
import run.aiwan.domain.Student;
import run.aiwan.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentdao;

    @Override
    @Transactional
    public List<Student> queryStudentsService() {
        List<Student> students = this.studentdao.queryStudents();
        
        // 可以去掉注释，验证事务
//        int a = 10 / 0;
        
        return students;
    }
}

```

