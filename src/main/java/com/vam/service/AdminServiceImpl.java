package com.vam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.mapper.AdminMapper;
import com.vam.model.BookVO;
import com.vam.model.CateVO;
import com.vam.model.Criteria;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;
	
	/* 상품 등록 */
	@Override
	public void bookEnroll(BookVO book) {
		
		log.info("service bookEnroll");
		
		adminMapper.bookEnroll(book);
	}
	/* 카테고리 리스트 */
	@Override
	public List<CateVO> cateList() {
			log.info("service cateList....");
		return adminMapper.cateList();
		
		/*
		 * 카테고리 리스트 데이터를 'goodsEnroll.jsp'에 전달해주어야 하기 때문에 해당 url 매핑 메서드에 카테고리 리스트 데이터를
		 * 반환하는 Service 메서드를 호출하여 List타입의 변수 'list'에 저장합니다(상품 등록 페이지 goodEnrollGET()).
		 */
	}
	
	/* 상품 리스트 */
	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		log.info("goodsGetTotalList()..........");
		return adminMapper.goodsGetList(cri);
	}

	/* 상품 총 갯수 */
	public int goodsGetTotal(Criteria cri) {
		log.info("goodsGetTotal().........");
		return adminMapper.goodsGetTotal(cri);
	}	
	
	/* 상품 조회 페이지 */
	@Override
	public BookVO goodsGetDetail(int bookId) {
		
		log.info("(service)bookGetDetail......." + bookId);
		
		return adminMapper.goodsGetDetail(bookId);
	}	
	
	
	/* 상품 정보 수정 */
	@Override
	public int goodsModify(BookVO vo) {
		
		log.info("goodsModify........");
		
		return adminMapper.goodsModify(vo);
		
	}
	
	
	/* 상품 정보 삭제 */
	@Override
	public int goodsDelete(int bookId) {

		log.info("goodsDelete..........");
		
		return adminMapper.goodsDelete(bookId);
	}		
	
	
	
	
}
