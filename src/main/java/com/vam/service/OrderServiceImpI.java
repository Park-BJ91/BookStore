package com.vam.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vam.mapper.AttachMapper;
import com.vam.mapper.BookMapper;
import com.vam.mapper.CartMapper;
import com.vam.mapper.MemberMapper;
import com.vam.mapper.OrderMapper;
import com.vam.model.AttachImageVO;
import com.vam.model.BookVO;
import com.vam.model.CartDTO;
import com.vam.model.MemberVO;
import com.vam.model.OrderCancelDTO;
import com.vam.model.OrderDTO;
import com.vam.model.OrderItemDTO;
import com.vam.model.OrderPageItemDTO;

@Service
public class OrderServiceImpI implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	@Override
	public List<OrderPageItemDTO> getGoodsInfo(List<OrderPageItemDTO> orders) {
		
		List<OrderPageItemDTO> result =  new ArrayList<OrderPageItemDTO>();
		
		for(OrderPageItemDTO ord : orders) {
			
			OrderPageItemDTO goodsInfo = orderMapper.getGoodsInfo(ord.getBookId());
			
			goodsInfo.setBookCount(ord.getBookCount());
			
			goodsInfo.initSaleTotal();
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(goodsInfo.getBookId());
			
			goodsInfo.setImageList(imageList);
			
			result.add(goodsInfo);
			
		}
		
		return result;
		
	}
	
	
	@Override
	@Transactional
	public void order(OrderDTO ord) {
		/* ����� �����Ͱ������� */
		/* ȸ�� ���� */
		MemberVO member = memberMapper.getMemberInfo(ord.getMemberId());
		/* �ֹ� ���� */
		List<OrderItemDTO> ords = new ArrayList<>();
		for(OrderItemDTO oit : ord.getOrders()) {
			OrderItemDTO orderItem = orderMapper.getOrderInfo(oit.getBookId());
			// ���� ����
			orderItem.setBookCount(oit.getBookCount());
			// �⺻���� ����
			orderItem.initSaleTotal();
			//List��ü �߰�
			ords.add(orderItem);
		}
		/* OrderDTO ���� */
		ord.setOrders(ords);
		ord.getOrderPriceInfo();
		
	/*DB �ֹ�,�ֹ���ǰ(,�������) �ֱ�*/
		
		/* orderId����� �� OrderDTO��ü orderId�� ���� */
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddmm");
		String orderId = member.getMemberId() + format.format(date);
		ord.setOrderId(orderId);
		
		/* db�ֱ� */
		orderMapper.enrollOrder(ord);		//vam_order ���
		for(OrderItemDTO oit : ord.getOrders()) {		//vam_orderItem ���
			oit.setOrderId(orderId);
			orderMapper.enrollOrderItem(oit);			
		}

	/* ��� ����Ʈ ���� ���� */
		
		/* ��� ���� & ���� ��(money) Member��ü ���� */
		int calMoney = member.getMoney();
		calMoney -= ord.getOrderFinalSalePrice();
		member.setMoney(calMoney);
		
		/* ����Ʈ ����, ����Ʈ ���� & ���� ����Ʈ(point) Member��ü ���� */
		int calPoint = member.getPoint();
		calPoint = calPoint - ord.getUsePoint() + ord.getOrderSavePoint();	// ���� ����Ʈ - ��� ����Ʈ + ȹ�� ����Ʈ
		member.setPoint(calPoint);
			
		/* ���� ��, ����Ʈ DB ���� */
		orderMapper.deductMoney(member);
		
	/* ��� ���� ���� */
		for(OrderItemDTO oit : ord.getOrders()) {
			/* ���� ��� �� ���ϱ� */
			BookVO book = bookMapper.getGoodsInfo(oit.getBookId());
			book.setBookStock(book.getBookStock() - oit.getBookCount());
			/* ���� �� DB ���� */
			orderMapper.deductStock(book);
		}			
		
	/* ��ٱ��� ���� */
		for(OrderItemDTO oit : ord.getOrders()) {
			CartDTO dto = new CartDTO();
			dto.setMemberId(ord.getMemberId());
			dto.setBookId(oit.getBookId());
			
			cartMapper.deleteOrderCart(dto);
		}			
		
	}
	
	/* �ֹ���� */
	@Override
	@Transactional
	public void orderCancel(OrderCancelDTO dto) {
		
		/* �ֹ�, �ֹ���ǰ ��ü */
		/*ȸ��*/
			MemberVO member = memberMapper.getMemberInfo(dto.getMemberId());
		/*�ֹ���ǰ*/
			List<OrderItemDTO> ords = orderMapper.getOrderItemInfo(dto.getOrderId());
			for(OrderItemDTO ord : ords) {
				ord.initSaleTotal();
			}
		/* �ֹ� */
			OrderDTO orw = orderMapper.getOrder(dto.getOrderId());
			orw.setOrders(ords);
			
			orw.getOrderPriceInfo();
			
	/* �ֹ���ǰ ��� DB */
			orderMapper.orderCancel(dto.getOrderId());
			
	/* ��, ����Ʈ, ��� ��ȯ */
			/* �� */
			int calMoney = member.getMoney();
			calMoney += orw.getOrderFinalSalePrice();
			member.setMoney(calMoney);
			
			/* ����Ʈ */
			int calPoint = member.getPoint();
			calPoint = calPoint + orw.getUsePoint() - orw.getOrderSavePoint();
			member.setPoint(calPoint);
			
				/* DB���� */
				orderMapper.deductMoney(member);
				
			/* ��� */
			for(OrderItemDTO ord : orw.getOrders()) {
				BookVO book = bookMapper.getGoodsInfo(ord.getBookId());
				book.setBookStock(book.getBookStock() + ord.getBookCount());
				orderMapper.deductStock(book);
			}
	}	
}
	
