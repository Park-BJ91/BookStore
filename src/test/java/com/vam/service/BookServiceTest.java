package com.vam.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vam.mapper.BookMapper;
import com.vam.model.BookVO;
import com.vam.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookServiceTest {

	@Autowired
	BookService service;
	
	@Test
	public void getCateInfoListTest1() {
		Criteria cri = new Criteria();
	
		String type = "TC";
		//String keyword = "�׽�Ʈ";
		String keyword = "ASMR";	
		String cateCode="104002";

		cri.setType(type);
		cri.setKeyword(keyword);
		cri.setCateCode(cateCode);
		
		System.out.println("List<CateFilterDTO> : " + service.getCateInfoList(cri));
		
	}
	
	@Test
	public void getCateInfoListTest2() {
		Criteria cri = new Criteria();
	
		String type = "AC";
		String keyword = "���϶�";	
		//String keyword = "�ӽ�ũ";	
		String cateCode = "103002";

		cri.setType(type);
		cri.setKeyword(keyword);
		cri.setCateCode(cateCode);
		
		System.out.println("List<CateFilterDTO> : " + service.getCateInfoList(cri));
		
	}	

	@Test
	public void getCateInfoListTest3() {
		Criteria cri = new Criteria();
	
		String type = "T";
		String keyword = "ttt";
		//String keyword = "����";	
		

		cri.setType(type);
		cri.setKeyword(keyword);
		
		System.out.println("List<CateFilterDTO> : " + service.getCateInfoList(cri));
		
	}	
	/*
	@Test
	public void getCateInfoListTest4() {
		Criteria cri = new Criteria();
	
		String type = "AC";
		//String keyword = "��ȫ��";	
		String keyword = "�ٳ�ī";	
		

		cri.setType(type);
		cri.setKeyword(keyword);
		
		System.out.println("List<CateFilterDTO> : " + service.getCateInfoList(cri));
		
	}
	*/
	@Test
	public void getGoodsInfoTest() {
		int bookId = 21;
		
		BookVO goodsInfo = service.getGoodsInfo(bookId);
		
		System.out.println("==���==");
		System.out.println("��ü : " + goodsInfo);
		System.out.println("bookId : " + goodsInfo.getBookId());
		System.out.println("�̹��� ���� : " + goodsInfo.getImageList());
	}
	
}