package com.vam.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vam.model.CartDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CartMapperTest {
	
	@Autowired
	private CartMapper mapper;
	
	@Test
	public void addCart() {
		String memberId = "admin";
		int bookId = 21;
		int count = 2;
		
		CartDTO cart = new CartDTO();
		cart.setMemberId(memberId);
		cart.setBookId(bookId);
		cart.setBookCount(count);
		
		int result = 0;
		try {
			result = mapper.addCart(cart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("��� : " + result);
		
	}	

	
	/* īƮ ���� */

	@Test
	public void deleteCartTest() {
		int cartId = 1;
		
		mapper.deleteCart(cartId);
	}	

	
	/* īƮ ���� ���� */

	@Test
	public void modifyCartTest() {
		int cartId = 3;
		int count = 5;
		
		CartDTO cart = new CartDTO();
		cart.setCartId(cartId);
		cart.setBookCount(count);
		
		mapper.modifyCount(cart);
		
	}

	
	/* īƮ ��� */ 

	@Test
	public void getCartTest() {
		String memberId = "admin";
		
		
		List<CartDTO> list = mapper.getCart(memberId);
		for(CartDTO cart : list) {
			System.out.println(cart);
			cart.initSaleTotal();
			System.out.println("init cart : " + cart);
		}
		
		
	
	}
			
	/* īƮ Ȯ�� */

	@Test
	public void checkCartTest() {
		
		String memberId = "admin";
		int bookId = 21;
		
		CartDTO cart = new CartDTO();
		cart.setMemberId(memberId);
		cart.setBookId(bookId);
		
		CartDTO resutlCart = mapper.checkCart(cart);
		System.out.println("��� : " + resutlCart);
		
	}

}
