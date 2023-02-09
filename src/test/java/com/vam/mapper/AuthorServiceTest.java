package com.vam.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vam.model.AuthorVO;
import com.vam.service.AuthorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorServiceTest {
	
	@Autowired
	private AuthorMapper authorMapper;
	
	@Autowired
	private AuthorService service;
	
	@Test
	public void authorModifyTest() throws Exception {
		 
		AuthorVO author = new AuthorVO();
		
		author.setAuthorId(1);
		System.out.println("service 수정 전..........." + service.authorGetDetail(author.getAuthorId()));
		
		author.setAuthorName("service 수정");
		author.setNationId("01");
		author.setAuthorIntro("service 소개 수정");
		
		service.authorModify(author);
		System.out.println("service 수정후.........." + service.authorGetDetail(author.getAuthorId()));
		
	}
}
