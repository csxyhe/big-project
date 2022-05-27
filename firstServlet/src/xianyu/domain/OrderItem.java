//用于封装咸鱼电商前台及后台订单的条目信息
package xianyu.domain;

public class OrderItem {
	private Order order;//订单
	private Product product;//商品
	private int buynum;//单个商品的购买数量
	
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
