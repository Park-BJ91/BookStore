package com.vam.controller;

import java.util.Random;


import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vam.model.MemberVO;
import com.vam.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/member")
@Log4j
public class MemberController {
	
	@Autowired
	private MemberService memberservice;
	
    @Autowired
    private JavaMailSender mailSender;
    

    @Autowired
    private BCryptPasswordEncoder pwEncoder;

	
	//ȸ������ ������ �̵�
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public void loginGET() {
		
		System.out.println("ȸ������ ������ ����");
		
	}
	
	//ȸ������
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinPOST(MemberVO member) throws Exception{
		
		/* 
		 * log.info("join ����"); 
		 * log.info("join Service ����");
		 */
		
        String rawPw = "";            // ���ڵ� �� ��й�ȣ
        String encodePw = "";        // ���ڵ� �� ��й�ȣ
        
        rawPw = member.getMemberPw();            // ��й�ȣ ������ ����
        encodePw = pwEncoder.encode(rawPw);        // ��й�ȣ ���ڵ�
        member.setMemberPw(encodePw);            // ���ڵ��� ��й�ȣ member��ü�� �ٽ� ����
		
		
		//ȸ������ ���� ���� 
		memberservice.memberJoin(member);
		
		return "redirect:/main";
	}
	
	// ���̵� �ߺ� �˻�
	@RequestMapping(value = "/memberIdChk", method = RequestMethod.POST)
	@ResponseBody
	public String memberIdChkPOST(String memberId) throws Exception{
		
		log.info("memberIdChk() ����");
		
		int result = memberservice.idCheck(memberId);
		
		log.info("����� = " + result);      
		
		if(result != 0) {
			
			return "fail";	// �ߺ� ���̵� ����
			
		} else {
			
			return "success";	// �ߺ� ���̵� x
			
		}	
		
	} // memberIdChkPOST() ����	
	
    /* ���������� �α׾ƿ� */
    @RequestMapping(value="logout.do", method=RequestMethod.GET)
    public String logoutMainGET(HttpServletRequest request) throws Exception{
        
        log.info("logoutMainGET�޼��� ����");
        
        HttpSession session = request.getSession();
        
        session.invalidate();
        
        return "redirect:/main";  
    }
    
    /* �񵿱��� �α׾ƿ� �޼��� */
    @RequestMapping(value="logout.do", method=RequestMethod.POST)
    @ResponseBody
    public void logoutPOST(HttpServletRequest request) throws Exception{
        
        log.info("�񵿱� �α׾ƿ� �޼��� ����");
        
        HttpSession session = request.getSession();
        
        session.invalidate();
        
    }
    
	
	//�α��� ������ �̵�
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void joinGET() {
		
		System.out.println("�α��� ������ ����");
		
	}
	
    /* �α��� */
    @RequestMapping(value = "login.do", method=RequestMethod.POST)
    public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
        
        //System.out.println("login �޼��� ����");
        //System.out.println("���޵� ������ : " + member);
        
        HttpSession session = request.getSession();
        String rawPw = "";
        String encodePw = "";
    
        MemberVO lvo = memberservice.memberLogin(member);    // �����Ѿ��̵�� ��ġ�ϴ� ���̵� �ִ��� 
        
        if(lvo != null) {            // ��ġ�ϴ� ���̵� �����
            
            rawPw = member.getMemberPw();        // ����ڰ� ������ ��й�ȣ
            encodePw = lvo.getMemberPw();        // �����ͺ��̽��� ������ ���ڵ��� ��й�ȣ
            
            if(true == pwEncoder.matches(rawPw, encodePw)) {        // ��й�ȣ ��ġ���� �Ǵ�
                
                lvo.setMemberPw("");                    // ���ڵ��� ��й�ȣ ���� ����
                session.setAttribute("member", lvo);     // session�� ������� ���� ����
                return "redirect:/main";        // ���������� �̵�
                
                
            } else {
 
                rttr.addFlashAttribute("result", 0);            
                return "redirect:/member/login";    // �α��� �������� �̵�
                
            }
            
        } else {                    // ��ġ�ϴ� ���̵� �������� ���� �� (�α��� ����)
            
            rttr.addFlashAttribute("result", 0);            
            return "redirect:/member/login";    // �α��� �������� �̵�
            
        }
    }
    /* �̸��� ���� */
    @RequestMapping(value="/mailCheck", method=RequestMethod.GET)
    @ResponseBody
    public String mailCheckGET(String email) throws Exception{
        
        /* ��(View)�κ��� �Ѿ�� ������ Ȯ�� */
        log.info("�̸��� ������ ���� Ȯ��");
        log.info("������ȣ : " + email);
        
        Random random = new Random();        
        int checkNum = random.nextInt(888888) + 111111;
        
        log.info("������ȣ " + checkNum);
        
        /* �̸��� ������ */
        String setFrom ="gothog@naver.com";
        String toMail = email;
        String title = "ȸ������ ���� �̸��� �Դϴ�.";
        String content = 
                "Ȩ�������� �湮���ּż� �����մϴ�." +
                "<br><br>" + 
                "���� ��ȣ�� " + checkNum + "�Դϴ�." + 
                "<br>" + 
                "�ش� ������ȣ�� ������ȣ Ȯ�ζ��� �����Ͽ� �ּ���.";
        
        try {
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);
            
        }catch(Exception e) {
            e.printStackTrace();
        }
                
        String num = Integer.toString(checkNum);
        return num;
    }
    
    
	
	
	
}
