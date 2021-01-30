package run.aiwan.dao;

import run.aiwan.domain.Sale;

public interface SaleDao {
    // 添加销售记录
    int insertSale(Sale sale);
}
