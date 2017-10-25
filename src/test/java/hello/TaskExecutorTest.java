package hello;

import java.util.Date;
import java.util.concurrent.Executor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class TaskExecutorTest {
	
	@Autowired
	ThreadPoolTaskExecutor executor;
	
	@Autowired
	TaskScheduler scheduler;

	//使用线程池，打印数据
	@Test
	public void printMessage() {
		for(int i = 0; i< 26; i++) {
			executor.execute(()->{
				System.out.println(Thread.currentThread().getName());
			});
		}
	}
	
	@Test
	public void testSchedulter() {
		System.out.println("schedule executor");
		scheduler.schedule(()->{
			System.out.println("i am schedule");
		}, new Date());
		
		scheduler.scheduleAtFixedRate(()->{
			System.out.println("i am scheduleAtFixedRate");
		}, 2000);
	}

}
