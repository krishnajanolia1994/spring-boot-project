package com.example.demoRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class DemoRestApplication {
	private static final Logger logger = LoggerFactory.getLogger(DemoRestApplication.class);
	public TaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setMaxPoolSize(500);
		executor.setCorePoolSize(20);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadGroupName("Async");
		return executor;
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoRestApplication.class, args);
		logger.info("this is loginfo");
		System.out.println("hfkjk ");
	}
}
