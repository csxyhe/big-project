//���ڱ����������ϵͳǰ̨����̨�Ķ�����ϸ��Ϣ
package xianyu.domain;
import java.util.ArrayList;
import java.util.List;
public class Order {
	private String id;//�������
	private double money;
	private String receiverAddress;
	private String receiverName;
	private String receiverEmail;
	//����֧��ʱ��ò�����ݿ���Լ����ɵģ������Կ�
	private User user;//�ö����������û�
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();//���Ŷ����й������Ʒ�����������ɵ�List
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public String getReceiverEmail() {
		return receiverEmail;
	}
	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}
	
	
}
