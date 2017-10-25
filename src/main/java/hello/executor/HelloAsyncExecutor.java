package hello.executor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class HelloAsyncExecutor {

	/**
	 * 异步方法调用，可具有参数,value用于指定用那个线程池执行的线程池名称
	 * @param i
	 */
	@Async("threadPoolTaskExecutor")
	public void asyncPrint(int i) {
		System.out.println("I am Asyncer，threadName："+Thread.currentThread().getName());
		throw new RuntimeException("我报错了，哈哈");
	}
}
