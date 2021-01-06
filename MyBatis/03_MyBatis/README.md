# 第三章

## 动态代理

在定义了接口的情况下，调用SqlSession 的 getMapper() 方法，即可获取指定接口的实现类对象。该方法的参数为指定 Dao 接口类的 class 值。

```
SqlSession session = factory.openSession();
StudentDao dao = session.getMapper(StudentDao.class);

// 使用工具类
StudentDao studentDao = MyBatisUtil.getSqlSession().getMapper(StudentDao.class);
```



## 参数的传递

- 一个参数

  #{任意字符}

- 多个参数

  @Param

  接口方法：

  List selectMultiParam(@Param("personName") String name, @Param("personAge") int age); 

  mapper 文件：  

  select id,name,email,age from student where name=#{personName} or age =#{personAge}

- 多个参数-使用对象

  接口方法： 

  List selectMultiObject(QueryParam queryParam); 

  mapper 文件：  

  > 对象的属性要一致

  select id,name,email,age from student where name=#{queryName} or age =#{queryAge}

- 多个参数-按位置

  接口方法： 

  List selectByNameAndAge(String name,int age); 

  mapper 文件:

  select id,name,email,age from student where name=#{arg0} or age =#{arg1}

- 多个参数-使用Map

  例如：

  Map data = new HashMap(); data.put(“myname”,”李力”); data.put(“myage”,20); 

  接口方法:

  List selectMultiMap(Map map);

  mapper 文件：

  select id,name,email,age from student where name=#{myname} or age =#{myage}



### # 和 $

- #：占位符，告诉 mybatis 使用实际的参数值代替。并使用 PrepareStatement 对象执行 sql 语句, #{…}代替 sql 语句的“?”。这样做更安全，更迅速，通常也是首选做法，

- $ 字符串替换，告诉 mybatis 使用$包含的“字符串”替换所在位置。使用 Statement 把 sql 语句和${}的 内容连接起来。主要用在替换表名，列名，不同列排序等操作。



### ResultType

resultType: 执行 sql 得到 ResultSet 转换的类型，使用类型的完全限定名或别名。 注意如果返回的是集 合，那应该设置为集合包含的类型，而不是集合本身。resultType 和 resultMap，不能同时使用。

```
<select id="selectById" resultType="com.bjpowernode.domain.Student">
 select id,name,email,age from student where id=#{studentId}
</select>
```

