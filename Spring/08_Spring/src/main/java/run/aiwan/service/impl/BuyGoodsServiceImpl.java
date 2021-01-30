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
