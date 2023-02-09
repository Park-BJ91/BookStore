package com.vam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vam.model.AuthorVO;
import com.vam.model.BookVO;
import com.vam.model.Criteria;
import com.vam.model.PageDTO;
import com.vam.service.AdminService;
import com.vam.service.AuthorService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/admin")
@Log4j
public class AdminController {
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AdminService adminService;
	
	
	
    /* ������ ���� ������ �̵� */
    @RequestMapping(value="main", method = RequestMethod.GET)
    public void adminMainGET() throws Exception{
        
        log.info("������ ������ �̵�");
        
    }
    
    /* ��ǰ ����(���) ������ ���� */
    @RequestMapping(value = "goodsManage", method = RequestMethod.GET)
    public void goodsManageGET(Criteria cri, Model model) throws Exception{
		/* ��ǰ ����Ʈ ������ */
		List list = adminService.goodsGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		/* ������ �������̽� ������ */
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.goodsGetTotal(cri)));
    }
    
    /* ��ǰ ��� ������ ���� */
    @RequestMapping(value = "goodsEnroll", method = RequestMethod.GET)
    public void goodsEnrollGET(Model model) throws Exception{
        log.info("��ǰ ��� ������ ����");
        
        //��ü �ʱ�ȭ
        ObjectMapper objm = new ObjectMapper();
        
        List list = adminService.cateList();
        
        //Java ��ü�� StringŸ���� JSON���� �����ͷ� ��ȯ���ݴϴ�. 
        String cateList = objm.writeValueAsString(list);
        
        //��(view)�� �����͸� �Ѱ��ֱ� ���ؼ� url ���� �޼����� �Ķ���Ϳ� Model�� �ο����� �� 
        //addAttribute()�� ����Ͽ� "cateList"�Ӽ��� StringŸ���� 'cateList' ������ ���� �����ŵ�ϴ�.
        model.addAttribute("cateList", cateList);
        
		//log.info("���� ��.........." + list);
		//log.info("���� ��.........." + cateList);
        
    }
    
    
    
    /* �۰� ��� ������ ���� */
    @RequestMapping(value = "authorEnroll", method = RequestMethod.GET)
    public void authorEnrollGET() throws Exception{
        log.info("�۰� ��� ������ ����");
    }
    
	/* �۰� ��� */
    @RequestMapping(value="authorEnroll.do", method = RequestMethod.POST)
    public String authorEnrollPOST(AuthorVO author, RedirectAttributes rttr) throws Exception{
    	
    	log.info("authorEnroll : " + author);
    	
    	authorService.authorEnroll(author);		//�۰���� ��������
    	
    	
    	rttr.addFlashAttribute("enroll_result" + author.getAuthorName());
    	
    	return "redirect:/admin/authorManage";
    	
    }
    
    /* �۰� ���� ������ ���� */
    @RequestMapping(value = "authorManage", method = RequestMethod.GET)
    public void authorManageGET(Criteria cri, Model model) throws Exception{
        log.info("�۰� ���� ������ ����..........." + cri);
        
		/* �۰� ��� ��� ������ */
        List list = authorService.authorGetList(cri);
        
		if(!list.isEmpty()) {
			model.addAttribute("list",list);	// �۰� ���� ���
		} else {
			model.addAttribute("listCheck", "empty");	// �۰� �������� ���� ���
		}
        
        /* ������ �̵� �������̽� ������ */
        int total = authorService.authorGetTotal(cri);
        
        PageDTO pageMaker = new PageDTO(cri, total);
        
        model.addAttribute("pageMaker", pageMaker);
        
    }
    
    
	/*
	 * ���� �������� �����ϰ� �۰� �� ���� ������ ��, vam_author���̺��� �ϳ��� �� �����͸� �����;� �մϴ�. �̴� ���� �����ÿ���
	 * '�۰� �� ������(authorDetail.jsp)' ó���� �Ͱ� �����մϴ�. ���� �ٽ� ������ Mapper, Service,
	 * Controller �޼��带 �ۼ����� �ʰ� ���� '�۰� �� ������'�� url ���� �޼����� authorGetInfoGET()��
	 * Ȱ���ϰڽ��ϴ�.
	 * 
	 * @GetMapping�� @PostMapping ���� URL�� �迭�� ó���� �� �ֽ��ϴ�.
	 */
    
	/* �۰� �� ������ */
	@GetMapping({"/authorDetail", "/authorModify"})
	public void authorGetInfoGET(int authorId, Criteria cri, Model model) throws Exception {
		
		log.info("authorDetail......." + authorId);
		
		/* �۰� ���� ������ ���� */
		model.addAttribute("cri", cri);
		
		/* ���� �۰� ���� */
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
		
	}
	
	/* �۰� ���� ���� */
	@PostMapping("/authorModify")
	public String authorModifyPOST(AuthorVO author, RedirectAttributes rttr) throws Exception{
		
		log.info("authorModifyPOST......." + author);
		
		int result = authorService.authorModify(author);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/authorManage";
		
	}
	
    /* ��ǰ ��� */
	@PostMapping("/goodsEnroll")
	public String goodsEnrollPOST(BookVO book, RedirectAttributes rttr) {
		
		log.info("goodsEnrollPOST......" + book);
		
		adminService.bookEnroll(book);
		
		rttr.addFlashAttribute("enroll_result", book.getBookName());
		
		return "redirect:/admin/goodsManage";
	}
	
	/* �۰� �˻� �˾�â */
	@GetMapping("/authorPop")
	public void authorPopGET(Criteria cri, Model model) throws Exception{
		
		log.info("authorPopGET.......");
		
		cri.setAmount(5);
		
		/* �Խù� ��� ������ */
		List list = authorService.authorGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list",list);	// �۰� ���� ���
		} else {
			model.addAttribute("listCheck", "empty");	// �۰� �������� ���� ���
		}
		
		
		/* ������ �̵� �������̽� ������ */
		model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));	
	
	}
	
	/* ��ǰ ��ȸ ������ */
	@GetMapping({"/goodsDetail", "/goodsModify"})
	public void goodsGetInfoGET(int bookId, Criteria cri, Model model) throws JsonProcessingException {
		
		log.info("goodsGetInfo()........." + bookId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		/* ī�װ� ����Ʈ ������ */
		model.addAttribute("cateList", mapper.writeValueAsString(adminService.cateList()));	
		
		/* ��� ������ ���� ���� */
		model.addAttribute("cri", cri);
		
		/* ��ȸ ������ ���� */
		model.addAttribute("goodsInfo", adminService.goodsGetDetail(bookId));
		
	}
	
	/* ��ǰ ���� ���� */
	@PostMapping("/goodsModify")
	public String goodsModifyPOST(BookVO vo, RedirectAttributes rttr) {
		
		log.info("goodsModifyPOST.........." + vo);
		
		int result = adminService.goodsModify(vo);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/goodsManage";		
		
	}
	
	/* ��ǰ ���� ���� */
	@PostMapping("/goodsDelete")
	public String goodsDeletePOST(int bookId, RedirectAttributes rttr) {
		
		log.info("goodsDeletePOST..........");
		
		int result = adminService.goodsDelete(bookId);
		
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/goodsManage";
		
	}
	
	/* �۰� ���� ���� */
	/*
	 �츮�� �����ϰ��� �ϴ� �����ʹ� �۰� ���̺�(vam_author)�� �������Դϴ�.
	 ������ �ܷ� Ű �������� ���� �۰� ���̺��� ����(reference) �ϰ� �ִ�  ��ǰ ���̺�(vam_book)�� �ִٴ� ���Դϴ�.
	 �������� �ʰ� �ִ� ���� ����ٸ� ������ ������ ���� �����ǰ� �ִ� ���� ������� �õ��� �Ѵٸ� 
	 '���Ἲ ���� ������ ����' �Ѵٴ� ���� �Բ� SQLIntegrityConstraintViolationException ���ܰ� �߻��մϴ�.
	 */
	@PostMapping("/authorDelete")
	public String authorDeletePOST(int authorId, RedirectAttributes rttr) {
		
		log.info("authorDeletePOST..........");
		
		int result = 0;
		
		try {
			
			result = authorService.authorDelete(authorId);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			result = 2;
			rttr.addFlashAttribute("delete_result", result);
			
			return "redirect:/admin/authorManage";
			
		}
		
		
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/authorManage";
		
	}	
    
    
}
