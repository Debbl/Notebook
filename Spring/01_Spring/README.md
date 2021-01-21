# Spring 概述

## 1 什么是 Spring

Spring是Java的开源框架，核心技术是ioc，aop。Spring也是一个容器，容器放的是Java对象。通过Spring框架可以实现解耦合，解决业务对象之间的耦合，也可以解决业务对象和非业务对象之间的耦合。

## 2 IoC 控制反转

- 把对象的创建，属性赋值，依赖关系都交给代码之外的容器，有容器实现对象的管理。把控制对象的权力转移给代码之外的容器。IoC是一个理论，概念，思想。
- IoC的重要技术实现DI（依赖注入）：通过名称就可以获取对象，对象创建，属性赋值都由容器实现
- Spring使用DI的技术，底层实现的反射机制

## 3 Spring 的体系结构

![](./img/spring-overview.png)

Spring 由 20 多个模块组成，它们可以分为数据访问/集成（DataAccess/Integration）、
Web、面向切面编程（AOP, Aspects）、提供JVM的代理（Instrumentation）、消息发送（Messaging）、 核心容器（Core Container）和测试（Test）。

