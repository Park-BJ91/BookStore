package com.vam.service;

import java.util.List;

import com.vam.model.AttachImageVO;

public interface AttachService {

	/* �̹��� ������ ��ȯ */
	public List<AttachImageVO> getAttachList(int bookId);
}