# 第五章

## MyBatis 配置文件



### 主配置文件

mybatis.xml

- 定义别名
- 数据源
- mapper 文件



### dataSource 标签

配置数据库的连接信息

```
<dataSource type="POOLED">...</dataSource>
```

POOLED 使用数据库连接池

UNPOOLED 不使用数据库连接池

JUNDI 使用JNDI实现数据源



### 事务

```
<transactionManager type="JDBC"/>
```



### 使用数据库属性的配置文件

创建jdbc.properties

使用key指定值-->语法${key}