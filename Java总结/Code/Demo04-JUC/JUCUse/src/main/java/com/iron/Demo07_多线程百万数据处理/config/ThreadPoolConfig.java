package com.iron.Demo07_多线程百万数据处理.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig
{

    /*
    @Value("${thread.pool.corePoolSize}")
    private String corePoolSize;

    @Value("${thread.pool.maxPoolSize}")
    private String maxPoolSize;

    @Value("${thread.pool.queueCapacity}")
    private String queueCapacity;

    @Value("${thread.pool.keepAliveSeconds}")
    private String keepAliveSeconds;
    */
    //线程池配置
    @Resource
    private ThreadPoolProperties threadPoolProperties;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor()
    {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();

        // 核心线程池大小
        threadPool.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        // 最大可创建的线程数
        threadPool.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
        // 等待队列最大长度
        threadPool.setQueueCapacity(threadPoolProperties.getQueueCapacity());
        // 线程池维护线程所允许的空闲时间
        threadPool.setKeepAliveSeconds(threadPoolProperties.getKeepAliveSeconds());
        //异步方法内部线程名称
        threadPool.setThreadNamePrefix("spring默认线程池-");
        // 线程池对拒绝任务(无线程可用)的处理策略
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 任务都完成再关闭线程池
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        // 任务初始化
        threadPool.initialize();

        return threadPool;
    }

}