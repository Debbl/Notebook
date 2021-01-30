package run.aiwan.dao;

import run.aiwan.domain.Goods;

public interface GoodsDao {
    // 更新库存
    int updateGoods(Goods goods);
    // 查询商品信息
    Goods selectGoods(Integer id);

}
