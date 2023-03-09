package com.vam.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class SelectDTO {
	
	/* ��ǰ id */
	private int bookId;
	
	/* ��ǰ �̸� */
	private String bookName;
	
	/* ī�װ� �̸� */
	private String cateName;
	
	private double ratingAvg;	
	
	/* ��ǰ �̹��� */
	private List<AttachImageVO> imageList;


}
