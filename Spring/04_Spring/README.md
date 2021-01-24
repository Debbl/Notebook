# DI 注入分类（基于注解）

## 1. 定义Bean的注解@Component

> ba01

### 1.1 类加注解

```java
//@Component 默认是类名首字母小写
//@Component("student")
@Component(value = "student")
public class Student {
    private String name;
    private int age;
    ...
```

>- @Repository 用于对DAO实现类进行注解
>- @Service 用于对 Service 实现类进行注解
>- @Controller 用于对 Controller 实现类进行注解
>
>- 这三个注解与@Component 都可以创建对象，但这三个注解还有其他的含义，@Service 创建业务层对象业务层对象可以加入事务功能，@Controller 注解创建的对象可以作为处 理器接收用户的请求。
>- @Repository，@Service，@Controller 是对@Component 注解的细化，标注不同层的对
>  象。即持久层对象，业务层对象，控制层对象。

### 1.2 配置组件扫描器

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

<!--包名，分隔符逗号、分号、空格-->
    <context:component-scan base-package="run.aiwan.ba01" />
</beans>
```

## 2. 简单类型属性注入@Value

> ba02

- 在类上

```java
@Component
public class Student {
    @Value("foo")
    private String name;
    @Value("21")
    private int age;
    ...
```

- 使用配置文件

```java
@Component
public class Student {
//    @Value("foo")
    @Value("${myName}")
    private String name;
//    @Value("21")
    @Value("${myAge}")
    private int age;
    ...
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

<!--包名，分隔符逗号、分号、空格-->
    <context:component-scan base-package="run.aiwan.ba02" />
    <context:property-placeholder location="classpath:ba02/student.properties" />
</beans>
```

## 3. 引用类型属性注入

- @Autowired默认是byType

> ba03

```java
@Component("student")
public class Student {
    @Value("foo")
    private String name;
    @Value("21")
    private int age;
    @Autowired
    private School school;
    ...
```

- byName

> ba04
>
> - 在属性上使用@Autowired
> - 在属性上使用@Qualifier(value="bean的id")

```java
@Component("student")
public class Student {
    @Value("foo")
    private String name;
    @Value("21")
    private int age;
    @Autowired
    @Qualifier("school")
    private School school;
    ...
```

## 4. @Resource

Spring提供了对jdk中@Resource注解的支持。@Resource注解既可以按名称匹配Bean，也可以按类型匹Bean。默认是按名称注入。默认是按名称注入。使用该注解，要求 JDK 必须是 6 及以上版本。 @Resource 可在属性上，也可在 set 方法上。

