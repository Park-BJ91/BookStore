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
		System.out.println("service ���� ��..........." + service.authorGetDetail(author.getAuthorId()));
		
		author.setAuthorName("service ����");
		author.setNationId("01");
		author.setAuthorIntro("service �Ұ� ����");
		
		service.authorModify(author);
		System.out.println("service ������.........." + service.authorGetDetail(author.getAuthorId()));
		
	}
}
