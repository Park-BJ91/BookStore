package com.vam.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vam.model.AttachImageVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminMapperTest {

	
	@Autowired
	private AdminMapper mapper;
	
	/* ��ǰ ��� */
	/*
	 
	@Test
	public void bookEnrollTest() throws Exception{
		
		BookVO book = new BookVO();
		
		book.setBookName("mapper �׽�Ʈ");
		book.setAuthorId(123);
		book.setPubleYear("2021-03-18");
		book.setPublisher("���ǻ�");
		book.setCateCode("0231");
		book.setBookPrice(20000);
		book.setBookStock(300);
		book.setBookDiscount(0.23);
		book.setBookIntro("å �Ұ� ");
		book.setBookContents("å ���� ");
		
		mapper.bookEnroll(book);
	}
	
	*/
	
	/* ī�װ� ����Ʈ */
	/*
	@Test
	public void cateListTest() throws Exception{
		
		System.out.println("cateList()..........." + mapper.cateList());
		
	}
	*/
	
	
	
	/* ��ǰ ����Ʈ & ��ǰ �� ���� */
	/*
	@Test
	public void goodsGetListTest() {
		
		Criteria cri = new Criteria();
		
		/* �˻����� */
		//cri.setKeyword("�׽�Ʈ");
		
		/* �˻� ����Ʈ */
		//List list = mapper.goodsGetList(cri);
		//for(int i = 0; i < list.size(); i++) {
			//System.out.println("result......." + i + " : " + list.get(i));
		//}
		
		/* ��ǰ �� ���� */
	/*
		int result = mapper.goodsGetTotal(cri);
		System.out.println("resutl.........." + result);
		
		
	}
	*/
	
	/* ��ǰ ���� ���� */
	/*
	@Test
	public void goodsModifyTest() {
		
		BookVO book = new BookVO();
		
		book.setBookId(1);
		book.setBookName("mapper �׽�Ʈ");
		book.setAuthorId(216);
		book.setPubleYear("2021-03-18");
		book.setPublisher("���ǻ�");
		book.setCateCode("103002");
		book.setBookPrice(30000);
		book.setBookStock(300);
		book.setBookDiscount(0.23);
		book.setBookIntro("å �Ұ� ");
		book.setBookContents("å ���� ");
		
		mapper.goodsModify(book);
		
	}
	*/
	/* ���� ��ǰ �̹��� ���� */
	/*
	@Test
	public void deleteImageAllTest() {
		
		int bookId = 3124;
		
		mapper.deleteImageAll(bookId);
		
	}
	*/
	/* ������ ��¥ �̹��� ����Ʈ */
	/*
	@Test
	public void checkImageListTest() {
		
		mapper.checkFileList();
		
	}
	*/
	
	/* ���� ��ǰ �̹��� ���� ��� */
	@Test
	public void getAttachInfoTest() {
		
		int bookId = 25;
		
		
		List<AttachImageVO> list = mapper.getAttachInfo(bookId);
		
		System.out.println("list : " + list);
		
	}
	
	
}
