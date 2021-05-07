# Python 匿名函数和高阶函数



#### 定义使用

```python
>>> func = lambda x, y: x + y
>>> func(1, 2)
3
```

#### sorted()

```python
sorted(iterable, key=None, reverse=False)
```

- iterable -- 可迭代对象。
- key -- 主要是用来进行比较的元素，只有一个参数，具体的函数的参数就是取自于可迭代对象中，指定可迭代对象中的一个元素来进行排序。
- reverse -- 排序规则，reverse = True 降序 ， reverse = False 升序（默认）

```python
>>> a
[('a', 4), ('b', 2), ('c', 5), ('d', 1)]
>>> sorted(a, key=lambda x: x[1])
[('d', 1), ('b', 2), ('a', 4), ('c', 5)]
>>> sorted(a, key=lambda x: x[1], reverse=True)
[('c', 5), ('a', 4), ('b', 2), ('d', 1)]
```

#### map()

```python
map(function, iterable, ...)
```

> 第一个参数 function 以参数序列中的每一个元素调用 function 函数，返回包含每次 function 函数返回值的新列表

```python
>>> a
[1, 2, 3, 4, 5]
>>> map(lambda x: x**2, a)
<map object at 0x000002AD06A3A7C0>
>>> res = map(lambda x: x**2, a)
>>> list(res)
[1, 4, 9, 16, 25]
```

```python
>>> a
[1, 2, 3, 4, 5]
>>> b
[5, 4, 3, 2, 1]
>>> res = map(lambda x, y: x + y, a, b)
>>> res
<map object at 0x000002AD06A72190>
>>> list(res)
[6, 6, 6, 6, 6]
```

#### reduce()

> **reduce()** 函数会对参数序列中元素进行累积。函数将一个数据集合（链表，元组等）中的所有数据进行下列操作：用传给 reduce 中的函数 function（有两个参数）先对集合中的第 1、2 个元素进行操作，得到的结果再与第三个数据用 function 函数运算，最后得到一个结果。

```python
reduce(function, iterable[, initializer])
```

- function -- 函数，有两个参数
- iterable -- 可迭代对象
- initializer -- 可选，初始参数

```python
>>>def add(x, y) :             # 两数相加
...     return x + y
... 
>>> reduce(add, [1,2,3,4,5])   # 计算列表和：1+2+3+4+5
15
>>> reduce(lambda x, y: x+y, [1,2,3,4,5])  # 使用 lambda 匿名函数
15
```

- 计算1-100，且第一个加数是100

```python
>>> from functools import reduce
>>> reduce(lambda x, y: x + y, range(1, 101), 100)
5150
```

#### filter()

> **filter()** 函数用于过滤序列，过滤掉不符合条件的元素，返回一个迭代器对象，如果要转换为列表，可以使用 **list()** 来转换。该接收两个参数，第一个为函数，第二个为序列，序列的每个元素作为参数传递给函数进行判，然后返回 True 或 False，最后将返回 True 的元素放到新列表中。

```python
filter(function, iterable)
```

- function -- 判断函数，返回值是布尔类型。
- iterable -- 可迭代对象。

```python
>>> a
[1, 0, 1, 1, 0, 1, 0, 1]
>>> res = filter(lambda x: True if x==1 else False, a)
>>> res
<filter object at 0x0000020515D85790>
>>> list(res)
[1, 1, 1, 1, 1]
```

