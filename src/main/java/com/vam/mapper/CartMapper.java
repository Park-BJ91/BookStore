package com.vam.mapper;

import java.util.List;

import com.vam.model.CartDTO;

public interface CartMapper {
	
	/* 카트 추가 */
	// vam_cart 테이블의 row를 추가하는 메서드입니다. row 추가 성공 시 1, 실패시 0을 반환
	public int addCart(CartDTO cart) throws Exception;
	
	/* 카트 삭제 */
	public int deleteCart(int cartId);
	
	/* 카트 수량 수정 */
	public int modifyCount(CartDTO cart);
	
	/* 카트 목록 */
	public List<CartDTO> getCart(String memberId);	
	
	/* 카트 확인 */
	//회원정보와 상품 정보를 넘겨서 해당하는 row가 있는지 확인
	public CartDTO checkCart(CartDTO cart);
	
	/* 카트 제거(주문) */
	public int deleteOrderCart(CartDTO dto);

}
