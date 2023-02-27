package com.vam.model;

import java.util.List;

public class CartDTO {
	
	//(cartId, memberId, bookId, booCount)는 vam_cart 테이블의 속성
    private int cartId;
    
    private String memberId;
    
    private int bookId;
    
    private int bookCount;
    
    //book
    //3개의 변수(bookName, bookPrice, bookDiscount)는 vam_book의 속성인데 
    //vam_cart 테이블과 조인을 하여 이 3개의 변수의 값을 장바구니 페이지에 뿌려주기 위해 추가
    private String bookName;
    
    private int bookPrice;
    
    private double bookDiscount;
    
    // 추가
    //salePrice, totalPrice)는 할인을 적용한 상품 한 개의 '판매 가격'과 판매 가격에 수량까지 곱한 '총 가격'을 의미
    // bookCount(수량), bookPrice(상품 가격), bookDiscount(할인율) 3개의 변수가 있다면 구 할 수 있는 변수
    //bookCount, bookPrice, bookDiscount 값이 초기화되었을 때 이 값들도 초기화할 수 있도록 하여서 뷰에 같이 뿌려주거나 서버에서 장바구니 관련 작업에 활용
    // salePrice와 totalPrice의 Setter 메서드를 생성X
    private int salePrice;
    
    private int totalPrice;
    
    //Getter
    private int point;
    
    private int totalPoint;
    
	/* 상품 이미지 */
	private List<AttachImageVO> imageList;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public double getBookDiscount() {
		return bookDiscount;
	}

	public void setBookDiscount(double bookDiscount) {
		this.bookDiscount = bookDiscount;
	}

	public int getSalePrice() {
		return salePrice;
	}


	public int getTotalPrice() {
		return totalPrice;
	}
	
	
	
	public int getPoint() {
		return point;
	}

	public int getTotalPoint() {
		return totalPoint;
	}
	


	public List<AttachImageVO> getImageList() {
		return imageList;
	}

	public void setImageList(List<AttachImageVO> imageList) {
		this.imageList = imageList;
	}

	//초기화
	//두 변수의 값을 변경을 원할 경우 오직 이 메서드를 통해서만 가능하도록 하기 위해,
	//salePrice와 totalPrice의 Setter 메서드 추가X
	//point totalpoint 선언한 변수에 값이 초기화될 수 있도록 initSaleTotal() 메서드 구현부에 아래의 코드를 추가
	public void initSaleTotal() {
		this.salePrice = (int) (this.bookPrice * (1-this.bookDiscount));
		this.totalPrice = this.salePrice*this.bookCount;
		this.point = (int)(Math.floor(this.salePrice*0.05));
		this.totalPoint = this.point * this.bookCount;
	}

	@Override
	public String toString() {
		return "CartDTO [cartId=" + cartId + ", memberId=" + memberId + ", bookId=" + bookId + ", bookCount="
				+ bookCount + ", bookName=" + bookName + ", bookPrice=" + bookPrice + ", bookDiscount=" + bookDiscount
				+ ", salePrice=" + salePrice + ", totalPrice=" + totalPrice + ", point=" + point + ", totalPoint="
				+ totalPoint + ", imageList=" + imageList + "]";
	}
	
	


    
}
