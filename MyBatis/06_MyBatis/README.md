# 第六章

## 扩展 PageHelper



### Maven 依赖

```
<dependency> 
    <groupId>com.github.pagehelper</groupId> 
    <artifactId>pagehelper</artifactId>
    <version>5.1.10</version>
</dependency>
```

### 加入 Plugin 配置

在 \<environment> 之前加入

```
<plugins>
	<plugin interceptor="com.github.pagehelper.PageInterceptor" />
</plugins>
```



### PageHelper 对象

查询语句之前调用 PageHelper.startPage 静态方法。除了 PageHelper.startPage 方法外，还提供了类似用法的 PageHelper.offsetPage 方法。 在你需要进行分页的 MyBatis 查询方法前调用 PageHelper.startPage 静态方法即可，紧跟在这个 方法后的第一个 MyBatis 查询方法会被进行分页。

```java
@Test 
public void testSelect() throws IOException { 
    //获取第 1 页，3 条内容 
    PageHelper.startPage(1,3); 
    List<Student> studentList = studentDao.selectStudents(); 
    studentList.forEach( stu -> System.out.println(stu));
}
```

