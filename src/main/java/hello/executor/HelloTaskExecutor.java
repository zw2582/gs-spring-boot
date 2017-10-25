package hello.executor;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.test.context.junit4.SpringRunner;


import hello.Application;

@Configuration
@EnableScheduling
public class HelloTaskExecutor {
	
	@Autowired
	HelloAsyncExecutor asyncExecutor;

	/**
	 * 定义定时调度
	 * @return
	 */
	@Bean
	public TaskScheduler getScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setBeanName("zhouweis scheduler");
		scheduler.setPoolSize(2);
		return scheduler;
	}
	
	/**
	 * 使用注解定时调度方法，使用@Scheduled之前，要加上@EnableScheduled
	 */
	@Scheduled(fixedRate = 2000L)
	public void schedulerPrint() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("i am scheduler,time:"+sdf.format(new Date())+",threadName:"+Thread.currentThread().getName());
		asyncExecutor.asyncPrint(12);
	}
	
	@Scheduled(fixedRate=2000)
	public void schedulerPrint2() throws InterruptedException {
		Thread.sleep(5000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("我是第二个定时任务,time:"+sdf.format(new Date())+",threadName:"+Thread.currentThread().getName());
	}
	
	@Scheduled(fixedRate=2000)
	public void schedulerPrint3() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("我是第三个定时任务,time:"+sdf.format(new Date())+",threadName:"+Thread.currentThread().getName());
	}
}
