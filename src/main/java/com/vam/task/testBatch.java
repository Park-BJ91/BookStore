package com.vam.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class testBatch {
	
	
	@Scheduled(cron = "0 0 1 * * *")
	public void testMethod() throws Exception{
		
		log.warn("��ġ ���� �׽�Ʈ ................");
		log.warn("============================");
	}
	
}