package com.vam.mapper;

import java.util.List;

import com.vam.model.AuthorVO;
import com.vam.model.Criteria;

public interface AuthorMapper {

    /* �۰� ��� */
    public void authorEnroll(AuthorVO author);
    
    
	/*
	 * �۰� ������ ��� ������ �����ϴ� �޼��带 �����մϴ�. ���� �۰��� �����͸� ��ȯ�޾ƾ� �ϱ� ������ ���� Ÿ������ List �ڷ� ������
	 * �����Ͽ����ϴ�. (List�� �迭�� ����� �ڷᱸ���Դϴ�. �迭���� �ٸ��� ���ԵǴ� �����Ϳ� ���� ũ�Ⱑ �������� ���մϴ�.) List��
	 * ����� �����Ͱ� AuthorVO(�۰� ����) ���� ����ϱ� ���� AuthorVO ���׸��� �����Ͽ����ϴ�
	 */
    
	/* �۰� ��� */
    public List<AuthorVO> authorGetList(Criteria cri);
    
	/*
	 * vam_author ���̺��� �� ���� ������ ���ϴ� ������ ȣ���ϴ� �޼��带 �����մϴ�. �� ���� ���� ��ȯ�޾ƾ� �ϱ� ������ �޼���
	 * ������� ���� Ÿ���� int�Դϴ�. �׸��� ���ǹ��� ����� 'keyword' �����͸� ���޹ޱ� ���� �Ķ���ͷ� Criteria Ŭ������
	 * �ο��Ͽ����ϴ�.
	 */
    
    /* �۰� �� �� */
    public int authorGetTotal(Criteria cri);
    
	/* �۰� �� */
    public AuthorVO authorGetDetail(int authorId);
    
	/* �۰� ���� ���� */
	public int authorModify(AuthorVO author);
	
	/* �۰� ���� ���� */
	public int authorDelete(int authorId);
    
} 
