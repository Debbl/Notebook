## DI注入分类（基于xml）

  bean 实例在调用无参构造器创建对象后，就要对 bean 对象的属性进行初始化。初始化是由容器自动完成的，称为注入。 根据注入方式的不同，常用的有两类：set 注入、构造注入。

### 1. set 注入

​	set 注入也叫设值注入是指，通过 setter 方法传入被调用者的实例。这种注入方式简单、直观，因而在 Spring 的依赖注入中大量使用。

注入：就是赋值的意思
简单类型： spring中规定java的基本数据类型和String都是简单类型。
di:给属性赋值

set注入（设值注入） ：spring调用类的set方法， 你可以在set方法中完成属性赋值

- 简单类型的set注入

  > ba01

 ```xml
<bean id="xx" class="yyy">
    <property name="属性名字" value="此属性的值"/>
    一个property只能给一个属性赋值
    <property....>
</bean>
 ```
- 引用类型set注入 

  > ba02

```xml
<bean id="xx" class="yyy">
    <property name="属性名字" ref="bean的id"/>
</bean>
```

### 2. 构造注入

> ba03

​	spring调用类有参数构造方法，在创建对象的同时，在构造方法中给属性赋值。
构造注入使用 \<constructor-arg> 标签
\<constructor-arg> 标签：一个\<constructor-arg>表示构造方法一个参数。
\<constructor-arg> 标签属性：

- name:表示构造方法的形参名
- index:表示构造方法的参数的位置，参数从左往右位置是 0 ， 1 ，2的顺序
- value：构造方法的形参类型是简单类型的，使用value
- ref：构造方法的形参类型是引用类型的，使用ref 

单独使用name和index都可以

```java
<bean id="student" class="run.aiwan.ba03.Student">
    <constructor-arg name="name" value="foo" />
    <constructor-arg name="age" value="23" />
    <constructor-arg name="school" ref="school" />
</bean>
```

### 3. 引用类型自动注入

> ba04 ba05

- byName按名称注入

把引用类型的`属性名称`当作是bean的`id`使用，从spring容器中获取这个bean赋值给引用类型

```java
    <bean id="student" class="run.aiwan.ba04.Student" autowire="byName">
        <property name="name" value="foo" />
        <property name="age" value="21" />
    </bean>
    
    <bean id="school" class="run.aiwan.ba04.School">
        <property name="name" value="清华大学" />
        <property name="address" value="北京的海淀区" />
    </bean>
```

- byType按类型注入

把和引用类型的数据类型同源的对象（类型一样、父子类关系的、接口和实现类关系的），从容器中获取，赋值给引用类型

```java
    <bean id="student" class="run.aiwan.ba04.Student" autowire="byType">
        <property name="name" value="foo" />
        <property name="age" value="21" />
    </bean>

    <bean id="school" class="run.aiwan.ba04.School">
        <property name="name" value="清华大学" />
        <property name="address" value="北京的海淀区" />
    </bean>
```

### 4. 多配置文件的使用方式

> ba06

一个总的配置文件，包含其他的多个配置文件

```xml
<!--    <import resource="spring-school.xml" />-->
<!--    <import resource="spring-student.xml" />-->
<!--    通配符-->
    <import resource="classpath:ba06/spring-*.xml" />
```

