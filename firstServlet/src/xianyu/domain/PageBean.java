package xianyu.domain;

import java.util.List;
import java.io.Serializable;
public class PageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int currentPage;// 当前页码
	private int totalPage;// 该类别总页码数
	private int currentNum;//当前页展示的最大商品数量
	private int totalNum;// 该类别总商品数量
	private List<Product> ps;// 该页的展示商品序列
	private String category;
	private String keyword;// 用于进行模糊搜索输入的内容

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
