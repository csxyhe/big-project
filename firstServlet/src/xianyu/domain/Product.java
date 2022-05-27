//创建一个Product类封装商品信息
package xianyu.domain;
public class Product {
	//由p_id和u_id共同构成products表的主键
	private String p_id;
	private int b_id;
	private String name;
	private String price;
	private String category;
	private int pnum;
	private String imgurl;
	private String description;
	
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return this.price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return this.category;
	}
	
	public void setPnum(int num) {
		this.pnum = num;
	}
	public int getPnum() {
		return this.pnum;
	}
	public String getImgurl() {
		return this.imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	
	
}
