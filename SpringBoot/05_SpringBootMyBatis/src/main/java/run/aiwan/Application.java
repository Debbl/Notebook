package run.aiwan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// Mybatis提供的注解：扫描数据持久层的 mapper映谢配置文件,DAO接口上就不用加@Mapper
// basePackages 通常指定到数据持久层包即可

// @MapperScan("run.aiwan.dao")
@MapperScan(basePackages = "run.aiwan.dao")

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
