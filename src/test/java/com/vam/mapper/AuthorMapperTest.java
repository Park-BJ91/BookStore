package com.vam.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vam.model.AuthorVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorMapperTest {

	
	@Autowired
	private AuthorMapper mapper;
	
	
	
	  /*
	@Test
	public void authorEnroll() throws Exception{
		
		AuthorVO aVO = new AuthorVO();
		
		aVO.setNationId("01");
		aVO.setAuthorName("주호민");
		aVO.setAuthorIntro("신과함께");
		
		mapper.authorEnroll(aVO);
		
	}
	  */
	
	   /* 작가 목록 테스트 */

//	@Test
 //   public void authorGetListTest() throws Exception{
        
        //Criteria cri = new Criteria(3,10);    // 3페이지 & 10개 행 표시
		//Criteria cri = new Criteria();
       // cri.setKeyword("하");
        
        //List<AuthorVO> list = mapper.authorGetList(cri);
        
       // for(int i = 0; i < list.size(); i++) {
       // System.out.println("list" + i + ".........." + list.get(i));
       // }
      // int total = mapper.authorGetTotal(cri);
       
      // System.out.println("total..............." + total);
//    }
	
   /*	
	@Test
	public void authorGetDetailTest() {
		
		int autId = 30;
		
		AuthorVO author = mapper.authorGetDetail(autId);
		
		System.out.println("author..............." + author);
	}
	 */
	
	
	/*
	@Test
	public void authorModifyTest() {
		
		AuthorVO author = new AuthorVO();
		
		author.setAuthorId(1);
		System.out.println("수정 전...................." + mapper.authorGetDetail(author.getAuthorId()));
		
		author.setAuthorName("수정");
		author.setNationId("01");
		author.setAuthorIntro("소개 수정 하였습니다.");
		
		mapper.authorModify(author);
		System.out.println("수정 후...................." + mapper.authorGetDetail(author.getAuthorId()));
		
	}*/
	
	
}
