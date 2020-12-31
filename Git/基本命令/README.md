### 一、基本概念

#### 1.1 工作区域（Working Directory）

#### 1.2 暂存区域（Stage）

#### 1.3 版本库（Repository）



### 二、基本使用

#### 2.1 git init

> 将当前目录初始化一个git仓库

```bash
git init
```



#### 2.2 git add

> 将当前所有的工作区的改动加入暂存区

```bash
git add (. | <file>)
```



#### 2.3 git commit

> 暂存区提交到版本库

```bash
git commit -m "<describe>"
```



#### 2.4 git remote & git push

> 如果未添加远程仓库，需要使用一下这个命令来添加远程仓库

```bash
git remote add origin <Repository address>
```

> 提交的远程仓库，如GitHub

```bash
git push -u origin master
```



### 三、其他命令

#### 3.1 git status

> 将查看当前三个区域的状态

```bash
git status [-s]
```



#### 3.2 git log

> 查看提交历史

```bash 
git log
```



#### 3.3 git reflog

> 查看提交历史包括已删除的提交

```bash
git reflog
```



#### 3.4 git reset

> 回退版本

```bash
git reset [--mixed | --soft | --hard] [HEAD]
```

##### 3.4.1 --mixed

> 默认选项，作用范围版本库、暂存区

##### 3.4.2 --soft

> 作用范围版本库

##### 3.4.3 --hard

> 作用范围版本库、暂存区 、工作区



#### 3.5 git refvert

> 撤销某一次中间的提交，不改变其他的提交，会生成一个新的提交

```bash
git revert HEAD
```



#### 3.6 git rm

> 删除工作区文件

##### 3.6.1 git rm

> 删除工作区文件，并且将这次删除放入暂存区

```bash
git rm <file>
```

##### 3.6.2 git rm -f

> 删除工作区和暂存区文件，并且将这次删除放入暂存区

```bash
git rm -f <file>
```

##### 3.6.3 git rm --cached

> 删除暂存区文件，但保留工作区的文件，并且将这次删除放入暂存区

```bash
git rm --chached <file>
```



#### 3.7 git diff

> 比较文件的不同，即暂存区和工作区的差异

```bash
git diff
```



### 四、多个分支

#### 4.1 git branch

> 查看分支

```bash
git branch [-a | -v]
```

#### 4.2 git switch

> 切换分支

```bash
git switch <branch>
```



#### 4.3 git checkout

> 切换分支或恢复工作树文件

##### 4.3.1 git checkout -b

```bash
git checkout -b <branch>
```

##### 4.3.2 git checkout

```bash
git checkout <branch>
```



#### 4.4 git merge

> 合并分支到当前分支

```bash
git merge <branch>
```



#### 4.5 git fetch

> 从远程仓库获取代码库

```bash
git fetch
```



#### 4.5 git pull

> 从远程获取代码并合并本地的版本
>
> 是`git fetch`和`git merge`的组合

```bash
git pull
```

> 提交其他分支，如开发版本

```bash
git pull origin <branch>
```



