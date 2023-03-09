package com.vam.service;

import com.vam.model.Criteria;
import com.vam.model.ReplyDTO;
import com.vam.model.ReplyPageDTO;

public interface ReplyService {
	
	/* ��� ��� */
	public int enrollReply(ReplyDTO dto);
	
	/* ��� ���� üũ */
	public String checkReply(ReplyDTO dto);
	
	/* ��� ����¡ */
	public ReplyPageDTO replyList(Criteria cri);
	
	/* ��� ���� */
	public int updateReply(ReplyDTO dto);
	
	/* ��� �Ѱ� ����(����������) */
	public ReplyDTO getUpdateReply(int replyId);
	
	/* ��� ���� */
	public int deleteReply(ReplyDTO dto);
	
	
}
