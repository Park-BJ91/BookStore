package com.vam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller

public class BookController {

	//���� ������ �̵�
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void mainPageGET() {
		
		System.out.println("���� ������ ����");
	}
}