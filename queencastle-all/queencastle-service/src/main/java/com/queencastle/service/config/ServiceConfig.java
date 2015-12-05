package com.queencastle.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.queencastle.service.impl.ServiceScanner;

@Configuration
@ComponentScan(basePackageClasses = ServiceScanner.class)
@EnableTransactionManagement
public class ServiceConfig {
    /**
     * 自定义一个RMI访问工具类
     * 
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate ret = new RestTemplate();
        return ret;
    }

    /**
     * 自定义一个线程池，用户调动所有的线程任务
     * 
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(8);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setKeepAliveSeconds(60 * 5);
        taskExecutor.setQueueCapacity(300);
        return taskExecutor;
    }
}
