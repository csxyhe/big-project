package xianyu.domain;

import java.util.List;
import java.io.Serializable;
public class PageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int currentPage;// ��ǰҳ��
	private int totalPage;// �������ҳ����
	private int currentNum;//��ǰҳչʾ�������Ʒ����
	private int totalNum;// ���������Ʒ����
	private List<Product> ps;// ��ҳ��չʾ��Ʒ����
	private String category;
	private String keyword;// ���ڽ���ģ���������������

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}



	public int getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}

	public List<Product> getPs() {
		return ps;
	}

	public void setPs(List<Product> ps) {
		this.ps = ps;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}



	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
