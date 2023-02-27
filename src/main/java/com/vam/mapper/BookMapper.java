package com.vam.mapper;

import java.util.List;

import com.vam.model.BookVO;
import com.vam.model.CateFilterDTO;
import com.vam.model.CateVO;
import com.vam.model.Criteria;

public interface BookMapper {

	/* ��ǰ �˻� */
	public List<BookVO> getGoodsList(Criteria cri);
	
	/* ��ǰ �� ���� */
	public int goodsGetTotal(Criteria cri);
	
	/* �۰� id ����Ʈ ��û */
	public String[] getAuthorIdList(String keyword);
	
	/* ���� ī�װ� ����Ʈ */
	public List<CateVO> getCateCode1();
	
	/* �ܱ� ī�װ� ����Ʈ */
	public List<CateVO> getCateCode2();
	
	/* �˻� ��� ī�װ� ����Ʈ */
	public String[] getCateList(Criteria cri);
	
	/* ī�װ� ����(+�˻���� ����) */
	public CateFilterDTO getCateInfo(Criteria cri);
	
	/* ��ǰ ���� */
	public BookVO getGoodsInfo(int bookId);
}
