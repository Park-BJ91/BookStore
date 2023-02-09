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
	
	
	
    /* 관리자 메인 페이지 이동 */
    @RequestMapping(value="main", method = RequestMethod.GET)
    public void adminMainGET() throws Exception{
        
        log.info("관리자 페이지 이동");
        
    }
    
    /* 상품 관리(목록) 페이지 접속 */
    @RequestMapping(value = "goodsManage", method = RequestMethod.GET)
    public void goodsManageGET(Criteria cri, Model model) throws Exception{
		/* 상품 리스트 데이터 */
		List list = adminService.goodsGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		/* 페이지 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.goodsGetTotal(cri)));
    }
    
    /* 상품 등록 페이지 접속 */
    @RequestMapping(value = "goodsEnroll", method = RequestMethod.GET)
    public void goodsEnrollGET(Model model) throws Exception{
        log.info("상품 등록 페이지 접속");
        
        //객체 초기화
        ObjectMapper objm = new ObjectMapper();
        
        List list = adminService.cateList();
        
        //Java 객체를 String타입의 JSON형식 데이터로 변환해줍니다. 
        String cateList = objm.writeValueAsString(list);
        
        //뷰(view)로 데이터를 넘겨주기 위해서 url 매핑 메서드의 파라미터에 Model를 부여해준 후 
        //addAttribute()를 사용하여 "cateList"속성에 String타입의 'cateList' 변수의 값을 저장시킵니다.
        model.addAttribute("cateList", cateList);
        
		//log.info("변경 전.........." + list);
		//log.info("변경 후.........." + cateList);
        
    }
    
    
    
    /* 작가 등록 페이지 접속 */
    @RequestMapping(value = "authorEnroll", method = RequestMethod.GET)
    public void authorEnrollGET() throws Exception{
        log.info("작가 등록 페이지 접속");
    }
    
	/* 작가 등록 */
    @RequestMapping(value="authorEnroll.do", method = RequestMethod.POST)
    public String authorEnrollPOST(AuthorVO author, RedirectAttributes rttr) throws Exception{
    	
    	log.info("authorEnroll : " + author);
    	
    	authorService.authorEnroll(author);		//작가등록 쿼리실행
    	
    	
    	rttr.addFlashAttribute("enroll_result" + author.getAuthorName());
    	
    	return "redirect:/admin/authorManage";
    	
    }
    
    /* 작가 관리 페이지 접속 */
    @RequestMapping(value = "authorManage", method = RequestMethod.GET)
    public void authorManageGET(Criteria cri, Model model) throws Exception{
        log.info("작가 관리 페이지 접속..........." + cri);
        
		/* 작가 목록 출력 데이터 */
        List list = authorService.authorGetList(cri);
        
		if(!list.isEmpty()) {
			model.addAttribute("list",list);	// 작가 존재 경우
		} else {
			model.addAttribute("listCheck", "empty");	// 작가 존재하지 않을 경우
		}
        
        /* 페이지 이동 인터페이스 데이터 */
        int total = authorService.authorGetTotal(cri);
        
        PageDTO pageMaker = new PageDTO(cri, total);
        
        model.addAttribute("pageMaker", pageMaker);
        
    }
    
    
	/*
	 * 수정 페이지도 동일하게 작가 한 명의 데이터 즉, vam_author테이블의 하나의 행 데이터를 가져와야 합니다. 이는 저번 포스팅에서
	 * '작가 상세 페이지(authorDetail.jsp)' 처리한 것과 동일합니다. 따라서 다시 일일이 Mapper, Service,
	 * Controller 메서드를 작성하지 않고 기존 '작가 상세 페이지'의 url 매핑 메서드인 authorGetInfoGET()을
	 * 활용하겠습니다.
	 * 
	 * @GetMapping과 @PostMapping 등은 URL을 배열로 처리할 수 있습니다.
	 */
    
	/* 작가 상세 페이지 */
	@GetMapping({"/authorDetail", "/authorModify"})
	public void authorGetInfoGET(int authorId, Criteria cri, Model model) throws Exception {
		
		log.info("authorDetail......." + authorId);
		
		/* 작가 관리 페이지 정보 */
		model.addAttribute("cri", cri);
		
		/* 선택 작가 정보 */
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
		
	}
	
	/* 작가 정보 수정 */
	@PostMapping("/authorModify")
	public String authorModifyPOST(AuthorVO author, RedirectAttributes rttr) throws Exception{
		
		log.info("authorModifyPOST......." + author);
		
		int result = authorService.authorModify(author);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/authorManage";
		
	}
	
    /* 상품 등록 */
	@PostMapping("/goodsEnroll")
	public String goodsEnrollPOST(BookVO book, RedirectAttributes rttr) {
		
		log.info("goodsEnrollPOST......" + book);
		
		adminService.bookEnroll(book);
		
		rttr.addFlashAttribute("enroll_result", book.getBookName());
		
		return "redirect:/admin/goodsManage";
	}
	
	/* 작가 검색 팝업창 */
	@GetMapping("/authorPop")
	public void authorPopGET(Criteria cri, Model model) throws Exception{
		
		log.info("authorPopGET.......");
		
		cri.setAmount(5);
		
		/* 게시물 출력 데이터 */
		List list = authorService.authorGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list",list);	// 작가 존재 경우
		} else {
			model.addAttribute("listCheck", "empty");	// 작가 존재하지 않을 경우
		}
		
		
		/* 페이지 이동 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));	
	
	}
	
	/* 상품 조회 페이지 */
	@GetMapping({"/goodsDetail", "/goodsModify"})
	public void goodsGetInfoGET(int bookId, Criteria cri, Model model) throws JsonProcessingException {
		
		log.info("goodsGetInfo()........." + bookId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		/* 카테고리 리스트 데이터 */
		model.addAttribute("cateList", mapper.writeValueAsString(adminService.cateList()));	
		
		/* 목록 페이지 조건 정보 */
		model.addAttribute("cri", cri);
		
		/* 조회 페이지 정보 */
		model.addAttribute("goodsInfo", adminService.goodsGetDetail(bookId));
		
	}
	
	/* 상품 정보 수정 */
	@PostMapping("/goodsModify")
	public String goodsModifyPOST(BookVO vo, RedirectAttributes rttr) {
		
		log.info("goodsModifyPOST.........." + vo);
		
		int result = adminService.goodsModify(vo);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/goodsManage";		
		
	}
	
	/* 상품 정보 삭제 */
	@PostMapping("/goodsDelete")
	public String goodsDeletePOST(int bookId, RedirectAttributes rttr) {
		
		log.info("goodsDeletePOST..........");
		
		int result = adminService.goodsDelete(bookId);
		
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/goodsManage";
		
	}
	
	/* 작가 정보 삭제 */
	/*
	 우리가 삭제하고자 하는 데이터는 작가 테이블(vam_author)의 데이터입니다.
	 문제는 외래 키 조건으로 인해 작가 테이블을 참조(reference) 하고 있는  상품 테이블(vam_book)이 있다는 점입니다.
	 참조되지 않고 있는 행을 지운다면 문제가 없지만 만약 참조되고 있는 행을 지우려고 시도를 한다면 
	 '무결성 제약 조건을 위반' 한다는 경고와 함께 SQLIntegrityConstraintViolationException 예외가 발생합니다.
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
