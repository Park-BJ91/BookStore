package com.vam.mapper;

import java.util.List;

import com.vam.model.CartDTO;

public interface CartMapper {
	
	/* īƮ �߰� */
	// vam_cart ���̺��� row�� �߰��ϴ� �޼����Դϴ�. row �߰� ���� �� 1, ���н� 0�� ��ȯ
	public int addCart(CartDTO cart) throws Exception;
	
	/* īƮ ���� */
	public int deleteCart(int cartId);
	
	/* īƮ ���� ���� */
	public int modifyCount(CartDTO cart);
	
	/* īƮ ��� */
	public List<CartDTO> getCart(String memberId);	
	
	/* īƮ Ȯ�� */
	//ȸ�������� ��ǰ ������ �Ѱܼ� �ش��ϴ� row�� �ִ��� Ȯ��
	public CartDTO checkCart(CartDTO cart);
	
	/* īƮ ����(�ֹ�) */
	public int deleteOrderCart(CartDTO dto);

}
