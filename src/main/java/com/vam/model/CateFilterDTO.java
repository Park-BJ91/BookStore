package com.vam.model;



public class CateFilterDTO {

	/* 카테고리 이름 */
	private String cateName;
	
	/* 카테고리 넘버 */
	private String cateCode;;
	
	/* 카테고리 상품 수 */
	private int cateCount;	
	
	/* 국내,국외 분류 */
	private String cateGroup;

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getCateCode() {
		return cateCode;
	}
	
	// cateGroup의 경우 cateCode변수에 값이 들어올때 값이 세팅이 도록 해주어야 합니다.
	//cateGroup은 국내의 경우 1, 국외의 경우 2가 값이 되도록 할 것입니다.
	// cateCode가 가지는 값들은 '101001','201001'과 같은 형식인데 제일 첫 번째 숫자가 국내, 국외
	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
		this.cateGroup = cateCode.split("")[0];
	}

	public int getCateCount() {
		return cateCount;
	}

	public void setCateCount(int cateCount) {
		this.cateCount = cateCount;
	}

	public String getCateGroup() {
		return cateGroup;
	}

	public void setCateGroup(String cateGroup) {
		this.cateGroup = cateGroup;
	}

	@Override
	public String toString() {
		return "CateFilterDTO [cateName=" + cateName + ", cateCode=" + cateCode + ", cateCount=" + cateCount
				+ ", cateGroup=" + cateGroup + "]";
	} 
	
}
