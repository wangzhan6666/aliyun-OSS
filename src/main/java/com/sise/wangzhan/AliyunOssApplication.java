package com.sise.wangzhan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// 由于我们没有在配置中设置数据库，要加上exclude = DataSourceAutoConfiguration.class，不然会报错
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AliyunOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliyunOssApplication.class, args);
    }

}
