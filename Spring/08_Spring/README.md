# Spring 事务

> 在07_Spring上添加

## 使用注解来实现事务

- 声明事务管理器、开启注解驱动

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd">
...    
	<!--spring的事务处理-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dateSource" />
    </bean>
    <!--开启事务注解驱动，告诉spring使用注解管理事务，创建代理对象 transaction-manager:事务管理器对象的id-->
    <tx:annotation-driven transaction-manager="transactionManager" />
```

- 业务层public方法加入事务属性

> propagation 传播行为
>
> - DEFAULT：采用 DB 默认的事务隔离级别。MySql 的默认为 REPEATABLE_READ； Oracle 默认为 READ_COMMITTED。
> - READ_UNCOMMITTED：读未提交。未解决任何并发问题。
> - READ_COMMITTED：读已提交。解决脏读，存在不可重复读与幻读。
> - REPEATABLE_READ：可重复读。解决脏读、不可重复读，存在幻读
> - SERIALIZABLE：串行化。不存在并发问题。
>
> isolation 隔离级别
>
> - PROPAGATION_REQUIRED 指定的方法必须在事务内执行。若当前存在事务，就加入到当前事务中；若当前没有事务，则创建一个新事务。这种传播行为是最常见的选择，也是 Spring 默认的事务传播行为。
> - PROPAGATION_SUPPORTS 指定的方法支持当前事务，但若当前没有事务，也可以以非事务方式执行。
> - PROPAGATION_REQUIRES_NEW 总是新建一个事务，若当前存在事务，就将当前事务挂起，直到新事务执行完毕。

```java
package run.aiwan.service.impl;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import run.aiwan.dao.GoodsDao;
import run.aiwan.dao.SaleDao;
import run.aiwan.domain.Goods;
import run.aiwan.domain.Sale;
import run.aiwan.exce.NotEnoughException;
import run.aiwan.service.BuyGoodsService;

public class BuyGoodsServiceImpl implements BuyGoodsService {

    private SaleDao saleDao;
    private GoodsDao goodsDao;

    public void setSaleDao(SaleDao saleDao) {
        this.saleDao = saleDao;
    }

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

//    @Transactional(
//            propagation = Propagation.REQUIRED,
//            isolation = Isolation.DEFAULT,
//            readOnly = false,
//            rollbackFor = {
//                    NullPointerException.class, NotEnoughException.class
//            }
//    )
    @Transactional
    @Override
    public void buy(Integer goodsId, Integer nums) {
        System.out.println("buy方法开始");

        // 添加销售记录 买了什么商品几个
        Sale sale = new Sale();
        sale.setGid(goodsId);
        sale.setNums(nums);
        this.saleDao.insertSale(sale);

        // 判断是否可以购买，goodsId对不对，nums是否小于库存
        Goods goods = this.goodsDao.selectGoods(goodsId);
        if (goods == null) {
            // goodsId不对，商品
            // System.out.println("没有这个商品" + goodsId);
            throw new NullPointerException("exception没有这个商品" + goodsId);
        } else if (goods.getAmount() < nums) {
            // System.out.println("库存不足");
            throw new NotEnoughException("exception库存不足");
        }

        // 更新库存 还有剩余的商品数量
        Goods buyGoods = new Goods();
        buyGoods.setId(goodsId);
        buyGoods.setAmount(nums);
        this.goodsDao.updateGoods(buyGoods);
        System.out.println("buy方法完成");
    }
}

```

## 注意

事务失效的几种情况

- 在针对事务的类中抛出RuntimeException异常，而不是抛出Exception
- 不能在方法中使用try catch抛出异常，不然不会回滚
- mysql默认存储引擎为MyISAM是不支持事务的， 需要设置为InnoDB模式

