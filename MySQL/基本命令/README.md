# MySQL



### DB、DBMS、SQL 分别是什么，他们有什么关系？

- DB

	DataBase (数据库，在硬盘上一文件的形式存在)

- DBMS

	DataBase Management System (数据库管理系统有MySQL等)

- SQL

	结构化查询语言，是一门标准通用的语言。标准的SQL适用于所有的数据库产品。SQL语句在执行的时候，实际上内部也会先进行编译，然后在执行SQL语句（由DBMS完成）



### 什么是表？

- table

	table 是数据库的基本组成单元，所有的数据都以表格的形式组织，目的是可读性强

	一个表包括行和列

- 内容

	行：被称为数据／记录（data）

	列： 被称为字段（column）

	| 学号（int） | 姓名（varchat） | 年龄（int） |
	| :---------: | :-------------: | :---------: |
	|     110     |      张三       |     20      |
	|     120     |      李四       |     21      |

	每一个字段应该包括字段名、数据类型、相关的约束



### 学习MySQL主要还是学习通用的SQL语句，那么SQL语句包括增删改查，SQL语句怎么分类？

- DQL （数据查询语言）

	查询语句，凡是select语句都是DQL

- DML（数据操作语言）

	insert	delete	update	对表当中的数据进行增删改

- DDL （数据定义语言）

	create	drop	alter	对表结构的增删改

- TCL （事务控制语言）

	commit	提交事务	rollback	回滚事务	（T是Transation）

- DCL （数据控制语言）

	grant	授权	revoke	撤销权限等



### MySQL 基本命令

- 登录MySQL

	```cmd
	mysql -uroot -p
	```

- 查看MySQL版本

	```mysql
	select version();
	```

- 查看有哪些数据库

	```mysql
	show database;
	```

- 创建数据库

	```sql
	create database 数据库名
	```

- 使用数据库

	```sql
	use 数据库名
	```

- 查看当前数据库中的表

	```sql
	show tables;
	```

- 查看当前使用的是哪个数据库

	```sql
	select database();
	```

- 初始化数据导入SQL脚本

	```sql
	source 文件地址
	```

- 删除数据库

	```sql
	drop database 数据库名
	```

- 查看表结构

	```sql
	desc 表名
	```

- 查看创建表语句

	```sql
	show create table 表名
	```

- 退出MySQL

	```sql
	exit
	```



### DQL

- 简单查询

	```sql
	select 字段一,字段二,字段三 from 表名;
	```

	```sql
	select 字段一 * 3 from 表名; # 数学运算
	```

	```sql
	select 字段一 as 一 from 表名; # 字段重命名
	```

- 条件查询

	```sql
	select 字段名 from 表名 where 条件;
	```

	```sql
	select 字段名 from 表名 where 字段名 between 小值 and 大值;
	```

	```sql
	 select 字段名 from 表名 where 字段名 is not null;
	```

	```sql
	select 字段名 from 表名 where 字段名 条件 or 条件;
	```

	```sql
	select 字段名 from 表名 where 字段名 not in(值,值);
	```

	```sql
	select 字段名 from 表名 where 字段名 like ''; # % 多个字符 _ 一个字符
	```

- 排序

	```sql
	select 字段名 from 表名 order by 字段名 asc; # 升序
	```

	```sql
	select 字段名 from 表名 order by 字段名 desc; # 降序
	```

- 分组函数/多行处理函数(多行-> 一行)

	```sql
	select sum(字段名) from 表名; # 求和
	```

	```sql
	select count(字段名) from 表名; # 计数
	```

	```sql
	select max(字段名) from 表名; # 最大值
	```

	```sql
	select min(字段名) from 表名; # 最小值
	```

- group by	和	having

	```sql
	 group by : 按照某个字段或者某些字段进行分组。
	 having : having是对分组之后的数据进行再次过滤。
	```

- DQL语句的执行顺序

	```sql
	  	select			5
	  		..
	  	from			1	
	  		..
	  	where			2
	  		..
	  	group by		3
	  		..
	  	having			4
	  		..
	  	order by		6
	  		..
	```



### DML

- 查询结果去重

	```sql
	select distinct 字段名 from 表名;
	```

- 连接查询

	```sql
	select 字段一，字段二 from 表名一 (inner/outer left/right)join 表名二 on 条件;
	```

- 子查询

	SQL语句嵌套查询

- union 

	将查询结果集相加

- 创建表

	```sql
	create table 表名(
		字段一 数据类型，
	    字段二 数据类型，
	    字段三 数据类型
	)
	```

	数据类型

	```sql
	int	        整数型(java中的int)
	bigint		长整型(java中的long)
	float		浮点型(java中的float double)
	char		定长字符串(String)
	varchar		可变长字符串(StringBuffer/StringBuilder)
	date		日期类型 （对应Java中的java.sql.Date类型）
	BLOB		二进制大对象（存储图片、视频等流媒体信息） Binary Large OBject （对应java中的Object）
	CLOB		字符大对象（存储较大文本，比如，可以存储4G的字符串。） Character Large OBject（对应java中的Object）
	```

- insert 语句插入数据

	```sql
	insert into 表名(字段一,字段二) values(值一,值二);
	```

- update 修改数据

	```sql
	update 表名 set 字段名=值 where 条件;
	```

- delete 删除数据

	```sql
	delete from 表名 where 条件;
	```

- 约束（Constraint）

	```sql
	非空约束(not null)：约束的字段不能为NULL
	唯一约束(unique)：约束的字段不能重复
	主键约束(primary key(字段名))：约束的字段既不能为NULL，也不能重复（简称PK）
	外键约束(foreign key(字段名) references 表名(字段名))：...（简称FK）
	检查约束(check)：注意Oracle数据库有check约束，但是mysql没有，目前mysql不支持该约束。
	```



### 三范式

- 第一范式

	任何一张表都应该有主键，并且每一个字段原子性不可再分。

- 第二范式

	建立在第一范式的基础上，所有非主键字段完全依赖主键，不能产生部分依赖。

	多个主键，只依赖其中一个主键，部分依赖。

	多对多？三张表，关系表两个外键。

- 第三范式

	建立在第二范式的基础上，所有非主键字段直接依赖主键，不能产生传递依赖。

	一对多？两张表，多的表加外键。

