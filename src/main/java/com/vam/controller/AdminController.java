package com.vam.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vam.model.AttachImageVO;
import com.vam.model.AuthorVO;
import com.vam.model.BookVO;
import com.vam.model.Criteria;
import com.vam.model.PageDTO;
import com.vam.service.AdminService;
import com.vam.service.AuthorService;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

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
		
		List<AttachImageVO> fileList = adminService.getAttachInfo(bookId);
		
		if(fileList != null) {
			
			List<Path> pathList = new ArrayList();
			
			fileList.forEach(vo ->{
				
				// ���� �̹���
				Path path = Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
				pathList.add(path);
				
				// ������ �̹���
				path = Paths.get("C:\\upload", vo.getUploadPath(), "s_" + vo.getUuid()+"_" + vo.getFileName());
				pathList.add(path);
				
			});
			
			pathList.forEach(path ->{
				path.toFile().delete();
			});
			
		}
		
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
	
	/* ÷�� ���� ���ε� */
	@PostMapping(value ="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {
		
		log.info("uploadAjaxActionPOST..........");
		/* �̹��� ���� üũ */
		for(MultipartFile multipartFile: uploadFile) {
			
			File checkfile = new File(multipartFile.getOriginalFilename());
			String type = null;
			
			try {
				type = Files.probeContentType(checkfile.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(!type.startsWith("image")) {
				
				
				List<AttachImageVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);				
			}
			
		}
		
		String uploadFolder = "C:\\upload";
		
		/* ��¥ ���� ��� */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		String datePath = str.replace("-", File.separator);
		
		/* ���� ���� */
		File uploadPath = new File(uploadFolder,datePath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();			
		}
		
		/* �̹��� ���� ��� ��ü */
		List<AttachImageVO> list = new ArrayList();
		
		for(MultipartFile multipartFile : uploadFile) {	
			
			/* �̹��� ���� ��ü */
			AttachImageVO vo = new AttachImageVO();			
			
			/* ���� �̸� */					
			String uploadFileName = multipartFile.getOriginalFilename();
			vo.setFileName(uploadFileName);
			vo.setUploadPath(datePath);
			
			
			/* uuid ���� ���� �̸� */
			String uuid = UUID.randomUUID().toString();
			vo.setUuid(uuid);
			
			uploadFileName = uuid +"_" + uploadFileName;
			
			/* ���� ��ġ, ���� �̸��� ��ģ File ��ü */ 
			File saveFile = new File(uploadPath, uploadFileName);
			
			/* ���� ���� */
			try {
				multipartFile.transferTo(saveFile);
				
				/* ����� ����(ImageIO) */
				/*
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
				
				BufferedImage bo_image = ImageIO.read(saveFile);
				BufferedImage bt_image = new BufferedImage(300, 500, BufferedImage.TYPE_3BYTE_BGR);
				
				Graphics2D graphic = bt_image.createGraphics();
				
				graphic.drawImage(bo_image, 0, 0,300,500, null);
					
				ImageIO.write(bt_image, "jpg", thumbnailFile);
				*/
				
				/* ��� 2 */
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);				
				
				BufferedImage bo_image = ImageIO.read(saveFile);
				
					//����
					double ratio = 3;
					//���� ����
					int width = (int)(bo_image.getWidth() / ratio);
					int height = (int)(bo_image.getHeight() / ratio);
				
				Thumbnails.of(saveFile)
		        .size(width, height)
		        .toFile(thumbnailFile);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			list.add(vo);
		}
		ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);
		
		return result;
	}
	
	/* �̹��� ���� ���� */
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName){
		
		log.info("deleteFile........" + fileName);
		
		File file = null;
		
		try {
			
		 file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
		
		 file.delete();
		 
			/* ���� ���� ���� */
			String originFileName = file.getAbsolutePath().replace("s_", "");
			
			log.info("originFileName : " + originFileName);
			
			file = new File(originFileName);
			
			file.delete();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			// ���ܰ� �߻��ߴٴ� �ǹ̴� �̹��� ���� ���� ��û�� ���������� ó������ ���Ͽ��ٴ� �ǹ��̱� ������
			//�����Ͽ��ٴ� ���� �˸����� return���� catch �����ο� �ۼ��Ͽ����ϴ�.
			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
			
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
    
    
}
