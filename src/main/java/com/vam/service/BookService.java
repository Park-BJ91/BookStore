package com.vam.service;

import java.util.List;

import com.vam.model.BookVO;
import com.vam.model.CateFilterDTO;
import com.vam.model.CateVO;
import com.vam.model.Criteria;
import com.vam.model.SelectDTO;

public interface BookService {

	/* ��ǰ �˻� */
	public List<BookVO> getGoodsList(Criteria cri);
	
	/* ��ǰ �� ���� */
	public int goodsGetTotal(Criteria cri);
	
	/* ���� ī�װ� ����Ʈ */
	public List<CateVO> getCateCode1();
	
	/* �ܱ� ī�װ� ����Ʈ */
	public List<CateVO> getCateCode2();
	
	/* �˻���� ī�װ� ���� ���� */
	public List<CateFilterDTO> getCateInfoList(Criteria cri);
	
	/* ��ǰ ���� */
	public BookVO getGoodsInfo(int bookId);
	
	/* ��ǰ id �̸� */
	public BookVO getBookIdName(int bookId);
	
	/* ���ܼ� ��ǰ ���� */
	public List<SelectDTO> likeSelect();
}
