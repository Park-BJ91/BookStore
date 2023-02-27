package com.vam.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vam.model.AttachImageVO;
import com.vam.model.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminServiceTest {

	
	@Autowired
	private AdminService service;
	

		
		/* ��ǰ ��� & ��ǰ �̹��� ��� �׽�Ʈ */
		@Test
		public void bookEnrollTEsts() {

			BookVO book = new BookVO();
			// ��ǰ ����
			book.setBookName("service �׽�Ʈ");
			book.setAuthorId(1);
			book.setPubleYear("2021-03-18");
			book.setPublisher("���ǻ�");
			book.setCateCode("202001");
			book.setBookPrice(20000);
			book.setBookStock(300);
			book.setBookDiscount(0.23);
			book.setBookIntro("å �Ұ� ");
			book.setBookContents("å ���� ");

			// �̹��� ����
			List<AttachImageVO> imageList = new ArrayList<AttachImageVO>(); 
			
			AttachImageVO image1 = new AttachImageVO();
			AttachImageVO image2 = new AttachImageVO();
			
			image1.setFileName("Test FileName3");
			image1.setUploadPath("test image 3");
			image1.setUuid("test11113");
			
			image2.setFileName("Test FileName4");
			image2.setUploadPath("test image 4");
			image2.setUuid("test22223");
			
			imageList.add(image1);
			imageList.add(image2);
			
			book.setImageList(imageList);        
			
			// bookEnroll() �޼��� ȣ��
			service.bookEnroll(book);
			
			System.out.println("��ϵ� VO : " + book);
			
			
		}
	}
