package com.vam.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReplyPageDTO {
	
	List<ReplyDTO> list;
	
	PageDTO pageInfo;

	
}
