package run.aiwan;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import run.aiwan.service.BuyGoodsService;

public class MyTest {
    @Test
    public void test01() {
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        BuyGoodsService buyGoodsService = (BuyGoodsService) ac.getBean("buyGoodsService");
        buyGoodsService.buy(1001, 10000);
    }
}
