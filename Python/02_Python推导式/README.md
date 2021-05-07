# Python 推导式

### 列表

- 生成一个1-10的列表

  ```python
  >>> a = [x for x in range(1, 11)]
  >>> a
  [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
  ```

- 生成一个1-10平方的列表

  ```python
  >>> a = [x ** 2 for x in range(1, 11)]
  >>> a
  [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]
  ```

- 生成一个1-10平方的列表且只包含偶数

  ```python
  >>> a = [x ** 2 for x in range(1, 11) if x**2 % 2 == 0]
  >>> a
  [4, 16, 36, 64, 100]
  ```

- 字符串

  ```python
  >>> a = [x + '1' for x in 'python']
  >>> a
  ['p1', 'y1', 't1', 'h1', 'o1', 'n1']
  ```

  > 笛卡尔积

  ```python
  >>> a = [x + y for x in 'ab' for y in '123']
  >>> a
  ['a1', 'a2', 'a3', 'b1', 'b2', 'b3']
  ```

### 元组

- 生成一个1-10的列表

  ```python
  >>> a = tuple(x ** 2 for x in range(1, 11))
  >>> a
  (1, 4, 9, 16, 25, 36, 49, 64, 81, 100)
  ```

- if

  ```python
  >>> a = tuple(x ** 2 for x in range(1, 11) if x != 1)
  >>> a
  (4, 9, 16, 25, 36, 49, 64, 81, 100)
  ```

### 字典

- key是1-10，value是1-10的平方

  ```python
  >>> dic = {x: x ** 2 for x in range(1, 11)}
  >>> dic
  {1: 1, 2: 4, 3: 9, 4: 16, 5: 25, 6: 36, 7: 49, 8: 64, 9: 81, 10: 100}
  ```

- if

  ```python
  >>> dic = {x: x ** 2 for x in range(1, 11) if x != 1}
  >>> dic
  {2: 4, 3: 9, 4: 16, 5: 25, 6: 36, 7: 49, 8: 64, 9: 81, 10: 100}
  ```

### 集合

- set

  ```python
  >>> set = {x for x in range(1, 11)}
  >>> set
  {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
  ```

- if

  ```python
  >>> set = {x for x in range(1, 11) if x != 1}
  >>> set
  {2, 3, 4, 5, 6, 7, 8, 9, 10}
  ```

- 不可重复

  ```python
  >>> set = {x for x in 'aabbcc'}
  >>> set
  {'c', 'a', 'b'}
  ```

  