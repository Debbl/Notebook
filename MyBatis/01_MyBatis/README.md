### 第一章

#### 1.1 三层架构

##### 介绍

- 界面层：和用户打交道的， 接收用户的请求参数， 显示处理结果的。（jsp ，html ，servlet）

- 业务逻辑层：接收了界面层传递的数据，计算逻辑，调用数据库，获取数据
- 数据访问层：就是访问数据库， 执行对数据的查询，修改，删除等等的。

##### 三层对应的包

- 界面层：controller包（servlet类）
- 业务逻辑层：service包（XXXService类）
- 数据访问层：dao包（XXXDao类）

##### 三层中类的交互

用户使用界面层 --> 业务逻辑 --> 数据访问层（持久层） --> 数据库（MySQL）

##### 三层中对应的处理框架

- 界面层--servlet--springmvc（框架）
- 业务逻辑层--service类--spring（框架）
- 数据访问层--dao类--MyBatis（框架）



#### 1.2 框架

   框架是一个舞台， 一个模版

#####    模版：

- 规定了好一些条款，内容。
-  加入自己的东西

#####    框架是一个模块

- 框架中定义好了一些功能。这些功能是可用的。
- 可以加入项目中自己的功能， 这些功能可以利用框架中写好的功能。

   框架是一个软件，半成品的软件，定义好了一些基础功能， 需要加入你的功能就是完整的。
   基础功能是可重复使用的，可升级的。

#####    框架特点：

- 框架一般不是全能的， 不能做所有事情

- 框架是针对某一个领域有效。 特长在某一个方面，比如mybatis做数据库操作强，但是他不能做其它的。

- 框架是一个软件



#### 1.3 MyBatis框架

一个框架，早期叫做ibatis,  代码在github。mybatis是 MyBatis SQL Mapper Framework for Java （sql映射框架）

- sql mapper :sql映射
  - 可以把数据库表中的一行数据  映射为 一个java对象。
  - 一行数据可以看做是一个java对象。操作这个对象，就相当于操作表中的数据

- Data Access Objects（DAOs） : 数据访问 ， 对数据库执行增删改查。



#### 1.4 MyBatis提供了哪些功能

  1. 提供了创建Connection ,Statement, ResultSet的能力 ，不用开发人员创建这些对象了

  2. 提供了执行sql语句的能力， 不用你执行sql

  3. 提供了循环sql， 把sql的结果转为java对象， List集合的能力
  ```
	while (rs.next()) {
		Student stu = new Student();
		stu.setId(rs.getInt("id"));
		stu.setName(rs.getString("name"));
		stu.setAge(rs.getInt("age"));
		//从数据库取出数据转为 Student 对象，封装到 List 集合
			stuList.add(stu);
	}
  ```

  4. 提供了关闭资源的能力，不用你关闭Connection, Statement, ResultSet

开发人员做的是： 提供sql语句
最后是： 开发人员提供sql语句--mybatis处理sql---开发人员得到List集合或java对象（表中的数据）



#### 总结：

mybatis是一个sql映射框架，提供的数据库的操作能力。增强的JDBC,使用mybatis让开发人员集中精神写sql就可以了，不必关心Connection,Statement,ResultSet的创建，销毁，sql的执行。
