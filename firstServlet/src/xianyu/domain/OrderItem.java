//���ڷ�װ�������ǰ̨����̨��������Ŀ��Ϣ
package xianyu.domain;

public class OrderItem {
	private Order order;//����
	private Product product;//��Ʒ
	private int buynum;//������Ʒ�Ĺ�������
	
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
