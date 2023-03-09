package com.vam.mapper;

import java.util.List;

import com.vam.model.BookVO;
import com.vam.model.MemberVO;
import com.vam.model.OrderDTO;
import com.vam.model.OrderItemDTO;
import com.vam.model.OrderPageItemDTO;

public interface OrderMapper {

	/* �ֹ� ��ǰ ���� */	
	public OrderPageItemDTO getGoodsInfo(int bookId);
	
	/* �ֹ� ��ǰ ����(�ֹ� ó��) */	
	public OrderItemDTO getOrderInfo(int bookId);
	
	/* �ֹ� ���̺� ��� */
	public int enrollOrder(OrderDTO ord);
	
	/* �ֹ� ������ ���̺� ��� */
	public int enrollOrderItem(OrderItemDTO orid);
	
	/* �ֹ� �ݾ� ���� */
	public int deductMoney(MemberVO member);
	
	/* �ֹ� ��� ���� */
	public int deductStock(BookVO book);
	
	/* �ֹ� ��� */
	public int orderCancel(String orderId);
	
	/* �ֹ� ��ǰ ����(�ֹ����) */
	public List<OrderItemDTO> getOrderItemInfo(String orderId);
	
	/* �ֹ� ����(�ֹ����) */
	public OrderDTO getOrder(String orderId);
	
	

	
}
