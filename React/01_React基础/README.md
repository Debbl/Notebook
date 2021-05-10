### 1. React 概述

#### 1.1 什么是 React 

React 是一个用于**构建用户界面**（HTML 页面前端）的 **Javascript 库**。React 主要用来写 HTML 页面，或构建Web应用。如果从 MVC 的角度来看，React 仅仅是视图层（V）,也就是只负责视图的喧嚷，而并非提供了完整的 M 和 C 的功能。React 起源于 Facebook 的内部项目，后又用来架设 Instagram 的网站，并于2013年5月开源

#### 1.2 React 的特点

- 声明式
- 基于组件
- 学习一次，随处可用

##### 1.2.1 声明式

你只需要描述 UI（HTML）看起来是什么样，就跟写 HTMl 一样，React 负责渲染 UI ，并在数据发生变化时更新 UI

```javascript
const jsx = <div className="app">
	<h1>Hello React! 动态变化数据: {count}</h1>
<div>
```

##### 1.2.2 基于组件

- 组件是 React 最重要的内容
- 组件表示页面中的部分内容
- 组合、复用多个组件，可以实现完整的页面功能

##### 1.2.3 学习一次，随处可用

- 使用 React 可以开发 Web 应用
- 使用 React 可以开发移动端原生的应用（react-native）
- 使用 React 可以开发 VR（虚拟现实）应用（react 360）

### 2. React 的基本使用

#### 2.1 React 的安装

- react 包是核心，提供创建元素、组件等功能
- react-dom 包提供 DOM 相关功能等

```bash
npm i react react-dom
```

#### 2.2 React 的使用

1.  引入 react 和react-dom 两个 js 文件

```
<script src="./node_modules/react/umd/react.development.js"></script>
<script src="./node_modules/react-dom/umd//react-dom.development.js"></script>
```

2.  创建 React 元素
3.  渲染 React 元素到页面中

```html
<div id="root"></div>
<script>
    const title = React.createElement('h1', null, 'Hello React')
    ReactDOM.render(title, document.getElementById('root'))
</script>
```

#### 2.3 方法说明

- React.createElement() 创建 raect  元素

```javascript
// 返回值: React  元素
// 第一个参数: 要创建 React 元素名称
// 第二个参数: 该 React 元素的属性
// 第三个参数及其以后的参数: 该 React 元素的子节点
const el = React.cerateElement('h1', {titile: '标题'}, 'Hello React')
```

- ReactDOM.render() 渲染 react 元素到页面中

```
// 第一个参数: 要渲染的 React 元素
// 第二个参数: DOM 对象，用于指定渲染到页面中的位置
ReactDOM.render(el, documetn.getElementById('root'))
```

### 3. React 脚手架的使用

#### 3.1 React 脚手架意义

1.  脚手架是开发 现代 Web 应用的必备
2.  充分利用 Webpack、Babel、ESLint 等工具辅助项目开发
3.  零配置，无需手动配置繁琐的工具即可使用
4.  关注业务，而不是手动配置

#### 3.2 使用 React 脚手架初始化项目

- 初始化项目

```bash
npx create-react-app my-app
```

- 启动项目

```bash
npm start
```

npx 命令介绍

- npm v5.2.0 引入的一条命令
- 目的：提升包内提供的命令行工具的体验
- 原来：先安装脚手架包，再使用这个包提供的命令
- 现在：无需安装脚手架包，就可以直接使用这个包提供的命令

#### 3.3  在脚手架中使用 React

1.  导入 react 和 react-dom 两个包

```
import React from 'react'
import ReactDOM from 'react-dom'
```

2.  创建 react 元素
3.  渲染 react 元素

```js
// 1. 导入react包
import React from 'react'
import ReactDOM from 'react-dom'


// 2. 创建react元素
const title = React.createElement('h1', null, 'Hello React !!!')
// 3. 渲染react元素
ReactDOM.render(title, document.getElementById('root'))
```

