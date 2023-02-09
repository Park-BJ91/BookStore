package com.vam.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vam.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTest {

	
	@Autowired
	private MemberMapper membermapper;			//MemberMapper.java �������̽� ������ ����
	
	//ȸ������ ���� �׽�Ʈ �޼���
	@Test
	public void memberJoin() throws Exception{
		MemberVO member = new MemberVO();
		
		member.setMemberId("test213");			//ȸ�� id
		member.setMemberPw("test213");			//ȸ�� ��й�ȣ
		member.setMemberName("test213");		//ȸ�� �̸�
		member.setMemberMail("test213");		//ȸ�� ����
		member.setMemberAddr1("test213");		//ȸ�� �����ȣ
		member.setMemberAddr2("test213");		//ȸ�� �ּ�
		member.setMemberAddr3("test213");		//ȸ�� ���ּ�
		
		membermapper.memberJoin(member);			//���� �޼��� ����
		
	}
}
