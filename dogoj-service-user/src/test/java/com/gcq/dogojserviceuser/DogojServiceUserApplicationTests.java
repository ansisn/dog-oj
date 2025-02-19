package com.gcq.dogojserviceuser;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//(exclude = {RedisAutoConfiguration.class})
@MapperScan("com.gcq.dogojserviceuser.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.gcq") //扫描到工具类
@SpringBootTest
class DogojServiceUserApplicationTests {

    @Test
    void contextLoads() {
    }

}
