package com.vam.mapper;

import java.util.List;

import com.vam.model.Criteria;
import com.vam.model.ReplyDTO;
import com.vam.model.UpdateReplyDTO;

public interface ReplyMapper {

	/* ��� ��� */
	public int enrollReply(ReplyDTO dto);
	
	/* ��� ���� üũ */
	public Integer checkReply(ReplyDTO dto);
	
	/* ��� ����¡ */
	public List<ReplyDTO> getReplyList(Criteria cri);
	
	/* ��� �� ����(����¡) */
	public int getReplyTotal(int bookId);
	
	/* ��� ���� */
	public int updateReply(ReplyDTO dto);
	
	/* ��� �Ѱ� ����(����������) */
	public ReplyDTO getUpdateReply(int replyId);
	
	/* ��� ���� */
	public int deleteReply(int replyId);
	
	/* ���� ��� ���ϱ� */
	public Double getRatingAverage(int bookId);
	
	/* ���� ��� �ݿ��ϱ� */
	public int updateRating(UpdateReplyDTO dto);
}
