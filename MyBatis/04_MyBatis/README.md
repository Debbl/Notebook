# 第四章

## 动态 SQL

动态 SQL，通过 MyBatis 提供的各种标签对条件作出判断以实现动态拼接 SQL 语句。这里的条件判断使用的表达式为OGNL 表达式。常用的动态 SQL 标签有\<if>、\<where>、\<choose/>、\<foreach>等。 MyBatis 的动态 SQL语句，与 JSTL 中的语句非常相似。 动态 SQL，主要用于解决查询条件不确定的情况：在程序运行期间，根据用户提交的查询条件进行查询。提交的查询条件不同，执行的 SQL 语句不同。若将每种可能的情况均逐一列出，对所有条件进行 排列组合，将会出现大量的 SQL 语句。此时，可使用动态 SQL 来解决这样的问题

### SQL-if

对于该标签的执行，当 test 的值为 true 时，会将其包含的 SQL 片断拼接到其所在的 SQL 语句中。
语法：\<if test="条件"> sql 语句的部分 \</if>

### SQL-where

\<if/>标签的中存在一个比较麻烦的地方：需要在 where 后手工添加 1=1 的子句。因为，若 where 后的所有\<if/>条件均为 false，而 where 后若又没有 1=1 子句，则 SQL 中就会只剩下一个空的 where，SQL 出错。所以，在where 后，需要添加永为真子句 1=1，以防止这种情况的发生。但当数据量很大时，会 严重影响查询效率。 

使用\<where/>标签，在有查询条件时，可以自动添加上 where 子句；没有查询条件时，不会添加where 子句需要注意的是，第一个\<if/>标签中的 SQL 片断，可以不包含 and。不过，写上 and 也不错， 系统会将多出的and 去掉。但其它\<if/>中 SQL 片断的 and，必须要求写上。否则 SQL 语句将拼接出错 。

语法：\<where> 其他动态 sql \</where>

### SQL-foreach

\<foreach/>标签用于实现对于数组与集合的遍历。对其使用，需要注意： 

- collection 表示要遍历的集合类型, list ，array 等。 简单类型和对象类型

- open、close、separator 为对遍历内容的 SQL 拼接。

语法：

 \<foreach collection="集合类型" open="开始的字符" close="结束的字符" item="集合中的成员" separator="集合成员之间的分隔符">
#{item 的值}
\</foreach>

### SQL-sql

\<sql/>标签用于定义 SQL 片断，以便其它 SQL 标签复用。而其它标签使用该 SQL 片断，需要使用
\<include/>子标签。该\<sql/>标签可以定义 SQL 语句中的任何部分，所以\<include/>子标签可以放在动态 SQL 的任何位置