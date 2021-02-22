# Go 变量

### Go 变量的声明

```
var 变量名 变量类型
```

### Go 语言的基本类型

- bool
- string
- int（32位操作系统为 int32、64位操作系统为 int32）、int8、int16、int32、int64
- uint、uint8、uint16、uint32、uint64、uintptr
- byte // uint8 的别名
- rune // int32 的别名 代表一个 Unicode 码
- float32、float64
- complex64、complex128  //  复数

|  类型  | 描述                                                         |
| :----: | :----------------------------------------------------------- |
| uint8  | 无符号 8位整型 (0 到 255)                                    |
| uint16 | 无符号 16位整型 (0 到 65535)                                 |
| uint32 | 无符号 32位整型 (0 到 4294967295)                            |
| uint64 | 无符号 64位整型 (0 到 18446744073709551615)                  |
|  int8  | 有符号 8位整型 (-128 到 127)                                 |
| int16  | 有符号 16位整型 (-32768 到 32767)                            |
| int32  | 有符号 32位整型 (-2147483648 到 2147483647)                  |
| int64  | 有符号 64位整型 (-9223372036854775808 到 9223372036854775807) |

### 批量声明

```go
var (
   a int
   b string
   c []float32
   d func() bool
   e struct{
      x int
   }
)
```

### 简短声明

- 定义变量，同时显式初始化。
- 不能提供数据类型。
- 只能用在函数内部。

```go
x := 1
i,j := 1,2
```

### 匿名变量

匿名变量的特点是一个下画线“_”，“_”本身就是一个特殊的标识符，被称为空白标识符。它可以像其他标识符那样用于变量的声明或赋值（任何类型都可以赋值给它），但任何赋给这个标识符的值都将被抛弃，因此这些值不能在后续的代码中使用，也不可以使用这个标识符作为变量对其它变量进行赋值或运算。使用匿名变量时，只需要在变量声明的地方使用下画线替换即可

```go
package main

import "fmt"

func GetData() (int, int) {
   return 100, 200
}

func main()  {
   a,_ := GetData()
   _,b := GetData()
   fmt.Println(a, b)
}
```

### 复数

```go
package main

import "fmt"

func main()  {
   x := complex(1, 2)
   fmt.Println(x)
   fmt.Println(real(x)) // 实部
   fmt.Println(imag(x)) // 虚部
}
```

### 字符串

|                方法                 |      介绍      |
| :---------------------------------: | :------------: |
|              len(str)               |     求长度     |
|           +或fmt.Sprintf            |   拼接字符串   |
|            strings.Split            |      分割      |
|          strings.contains           |  判断是否包含  |
| strings.HasPrefix,strings.HasSuffix | 前缀/后缀判断  |
| strings.Index(),strings.LastIndex() | 子串出现的位置 |
| strings.Join(a[]string, sep string) |    join操作    |

```go
package main

import "fmt"

func main() {
	x := "Hello"
	y := `零
一
二
三
`
	fmt.Println(x)
	fmt.Println(y)
	fmt.Println(len(x)) // 获取字符串的长度
}

```



# Go 常量

```
const name [type] = value
```

```go
package main

import "fmt"

func main() {
   const (
      e = 2.71828
      Pi = 3.14159
   )

   fmt.Println(e, Pi)
}
```

iota 常量生成器

`iota`在const关键字出现时将被重置为0。const中每新增一行常量声明将使`iota`计数一次(iota可理解为const语句块中的行索引)。 使用iota能简化定义，在定义枚举时很有用。

```go
package main

import "fmt"

func main() {
   const (
      n1 = iota //0
      n2        //1
      _
      n4        //3
   )
   fmt.Println(n1, n2, n4)
}
```