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



### ResultMap

resultMap 可以自定义 sql 的结果和 java 对象属性的映射关系。更灵活的把列值赋值给指定属性。 常用在列名和 java 对象属性名不一样的情况。

mapper文件

```
<!-- 创建 resultMap
 id:自定义的唯一名称，在<select>使用
 type:期望转为的 java 对象的全限定名称或别名
-->
<resultMap id="studentMap" type="com.bjpowernode.domain.Student">
 <!-- 主键字段使用 id -->
 <id column="id" property="id" />
 <!--非主键字段使用 result-->
 <result column="name" property="name"/>
 <result column="email" property="email" />
 <result column="age" property="age" />
</resultMap>

<!--resultMap: resultMap 标签中的 id 属性值-->
<select id="selectUseResultMap" resultMap="studentMap">
 select id,name,email,age from student where name=#{queryName} or
age=#{queryAge}
</select>
```



### typeAliases

类型别名

Mybatis 支持默认别名，我们也可以采用自定义别名方式来开发，主要使用在 mybatis.xml 主配置文件定义别名：

- typeAlias
- package

```
<typeAliases>
 <!--
 定义单个类型的别名
 type:类型的全限定名称
 alias:自定义别名
 -->
 <typeAlias type="com.bjpowernode.domain.Student" alias="mystudent"/>
 <!--
 批量定义别名，扫描整个包下的类，别名为类名（首字母大写或小写都可以）
 name:包名
 -->
 <package name="com.bjpowernode.domain"/>
 <package name="...其他包"/>
</typeAliases>
```

mapper.xml

```
<select id="selectStudents" resultType="mystudent">
 select id,name,email,age from student
</select>
```
