package com.example.demoRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class DemoRestApplication {
	
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

		System.out.println("hfkjk ");
	}
}
