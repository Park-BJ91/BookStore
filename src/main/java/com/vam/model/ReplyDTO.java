package com.vam.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class ReplyDTO {
	
	private int replyId;
	
	private int bookId;
	
	private String memberId;

	/* Gson, Jackson 라이브러리를 사용해서 Date, LocalDateTime 타입의 데이터를 
	 * json으로 변환할 경우 "yyyy-MM-dd" 형식 변환되지 않음 
	 */
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date regDate;
	
	private String content;
	
	/* 평점 */
	private double rating;
	

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "ReplyDTO [replyId=" + replyId + ", bookId=" + bookId + ", memberId=" + memberId + ", regDate=" + regDate
				+ ", content=" + content + ", rating=" + rating + "]";
	}
	
	

}
