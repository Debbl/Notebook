# Redis

## 基本数据类型

key-value存储系统，value有五种形式

- String：字符串
- Hash：hash map
- List：列表
- Set：集合
- Sored Set：有序集合



### String

设置

```
127.0.0.1:6379> set k1 v1
OK
```

查询

```
127.0.0.1:6379> get k1
"v1"
```

修改

```
127.0.0.1:6379> set k1 vv1
OK
```

删除

```
127.0.0.1:6379> del k1
(integer) 1
```

批量设置

 ```
127.0.0.1:6379> mset k1 v1 k2 v2 k3 v3
OK
 ```

批量获取

```
127.0.0.1:6379> mget k1 k2
1) "v1"
2) "v2"
```



### Hash

value是一个对象

设置

```
127.0.0.1:6379> hset h1 name liming age 18
(integer) 2
```

查询

```
127.0.0.1:6379> hget h1 name
"liming"
```

修改

```
127.0.0.1:6379> hset h1 name li
(integer) 0
127.0.0.1:6379> hget h1 name
"li"
```

多个

```
127.0.0.1:6379> hmset h1 name zs age 18
OK
```

获取所有字段

```
127.0.0.1:6379> hgetall h1
1) "name"
2) "zs"
3) "age"
4) "18"
```

删除

```
127.0.0.1:6379> hdel h1 age
(integer) 1
127.0.0.1:6379> hgetall h1
1) "name"
2) "zs"
127.0.0.1:6379> del h1
(integer) 1
```



### list

链表

从左进

```
127.0.0.1:6379> lpush l1 v1
(integer) 1
```

查询下标从0到0

```
127.0.0.1:6379> lrange l1 0 0
1) "v1"
```

```
127.0.0.1:6379> lpush l1 v2 v3 v4
(integer) 4
```

```
127.0.0.1:6379> lrange l1 0 2
1) "v4"
2) "v3"
3) "v2"
```

从右进

```
127.0.0.1:6379> rpush l1 v5
(integer) 5
127.0.0.1:6379> lrange l1 0 4
1) "v4"
2) "v3"
3) "v2"
4) "v1"
5) "v5"
```

查询全部

```
127.0.0.1:6379> lrange l1 0 -1
1) "v4"
2) "v3"
3) "v2"
4) "v1"
5) "v5"
```

从左出

```
127.0.0.1:6379> lrange l1 0 -1
1) "v4"
2) "v3"
3) "v2"
4) "v1"
5) "v5"
127.0.0.1:6379> lpop l1
"v4"
```

从右出

```
127.0.0.1:6379> lrange l1 0 -1
1) "v3"
2) "v2"
3) "v1"
4) "v5"
127.0.0.1:6379> rpop l1
"v5"
```



### Set

集合（不能有相同的元素）

添加元素

```
127.0.0.1:6379> sadd s1 v1
(integer) 1
```

列出所有元素

```
127.0.0.1:6379> smembers s1
1) "v1"
127.0.0.1:6379> sadd s1 v2 v2 v3
(integer) 2
127.0.0.1:6379> smembers s1
1) "v3"
2) "v2"
3) "v1"
```



### Sored Set

- zadd

- zrange

```
127.0.0.1:6379> ZADD z1 1 v1
(integer) 1
127.0.0.1:6379> zadd z1 2 v2
(integer) 1
127.0.0.1:6379> zadd z1 3 v3
(integer) 1
127.0.0.1:6379> zrange z1 0 3
1) "v1"
2) "v2"
3) "v3"
```



## 密码设置和查看

```
127.0.0.1:6379> config set requirepass 111111
OK
127.0.0.1:6379> config get requirepass
(error) NOAUTH Authentication required.
127.0.0.1:6379> auth 111111
OK
127.0.0.1:6379> config get requirepass
1) "requirepass"
2) "111111"
```

密码登录

```
redis-cli -p 6379 -a 111111
```



## 订阅和发布

Redis 通过监听一个 TCP 端口或者 Unix socket 的方式来接收来自客户端的连接，当一个连接建立后，Redis 内部会进行以下一些操作：

- 首先，客户端 socket 会被设置为非阻塞模式，因为 Redis 在网络事件处理上采用的是非阻塞多路复用模型。
- 然后为这个 socket 设置 TCP_NODELAY 属性，禁用 Nagle 算法
- 然后创建一个可读的文件事件用于监听这个客户端 socket 的数据发送

订阅

```
127.0.0.1:6379> SUBSCRIBE foo
Reading messages... (press Ctrl-C to quit)
1) "subscribe"
2) "foo"
3) (integer) 1
1) "message"
2) "foo"
```

发布

```
127.0.0.1:6379> PUBLISH foo hahaha
(integer) 1
```

结果

```
127.0.0.1:6379> SUBSCRIBE foo
Reading messages... (press Ctrl-C to quit)
1) "subscribe"
2) "foo"
3) (integer) 1
1) "message"
2) "foo"
3) "hahaha"
```

通配符订阅

```
127.0.0.1:6379> PSUBSCRIBE a*b
Reading messages... (press Ctrl-C to quit)
1) "psubscribe"
2) "a*b"
3) (integer) 1
```

发布

```
127.0.0.1:6379> PUBLISH axxxxb hahaha
(integer) 1
```

结果

```
127.0.0.1:6379> PSUBSCRIBE a*b
Reading messages... (press Ctrl-C to quit)
1) "psubscribe"
2) "a*b"
3) (integer) 1
1) "pmessage"
2) "a*b"
3) "axxxxb"
4) "hahaha"
```



## Key

查询key

```
127.0.0.1:6379> keys s1
1) "s1"
```

查询所有key

```
127.0.0.1:6379> keys *
```



### 补充

参考 https://www.bilibili.com/video/BV1hJ411K72h

在线工具 https://redislabs.com/

客户端 Redis Desktop Manager



