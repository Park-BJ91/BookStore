package com.vam.model;



public class CateFilterDTO {

	/* ī�װ� �̸� */
	private String cateName;
	
	/* ī�װ� �ѹ� */
	private String cateCode;;
	
	/* ī�װ� ��ǰ �� */
	private int cateCount;	
	
	/* ����,���� �з� */
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
	
	// cateGroup�� ��� cateCode������ ���� ���ö� ���� ������ ���� ���־�� �մϴ�.
	//cateGroup�� ������ ��� 1, ������ ��� 2�� ���� �ǵ��� �� ���Դϴ�.
	// cateCode�� ������ ������ '101001','201001'�� ���� �����ε� ���� ù ��° ���ڰ� ����, ����
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
