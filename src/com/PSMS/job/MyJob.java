package com.PSMS.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//@Component
public class MyJob {
	
	//@Scheduled(cron = "0 0/1 * * * ?")
	public void sendMessage(){
		System.out.println("=========================我在测试定时任务==========================");
		System.out.println();
		System.out.println("=========================我在测试定时任务==========================");
	}
}
