package com.vam.mapper;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookMapperTest {
	
	
	@Autowired
	private BookMapper mapper;
	
	/* 작가 id 리스트 요청 */
	/*
	@Test
	public void getAuthorId() {
		
		String keyword = "박";
		
		String[] list = mapper.getAuthorIdList(keyword);
		
		System.out.println("결과 : " + list.toString());
		
		for(String id : list) {
			System.out.println("개별 결과 : " + id);
		}
		
		
	}
	*/
	
	/* 카테고리 리스트 */
	/*
	@Test
	public void getCateListTest1() {
		
		Criteria cri = new Criteria();
		
		String type = "TC";
		String keyword = "AAA";
		//String type = "A";
		//String keyword = "유홍준";		

		cri.setType(type);
		cri.setKeyword(keyword);
		//cri.setAuthorArr(mapper.getAuthorIdList(keyword));		
		
		String[] cateList = mapper.getCateList(cri)		;
		for(String codeNum : cateList) {
			System.out.println("codeNum ::::: " + codeNum);
		}
		
		
	}
	*/
	/* 카테고리 정보 얻기 */	
	/*
	@Test
	public void getCateInfoTest1() {
		
		Criteria cri = new Criteria();
		
		String type = "TC";
		String keyword = "ASMR";	
		String cateCode="204001";

		cri.setType(type);
		cri.setKeyword(keyword);
		cri.setCateCode(cateCode);
		
		mapper.getCateInfo(cri);
		
	}
	*/
	
	/*
	@Test
	public void getGoodsInfoTest() {
		int bookId = 21;
		BookVO VO = mapper.getGoodsInfo(bookId);
		System.out.println("===============");
		System.out.println(VO);
		System.out.println("===============");
	}
	*/
	

}
