//用于保存咸鱼电商系统前台及后台的订单详细信息
package xianyu.domain;
import java.util.ArrayList;
import java.util.List;
public class Order {
	private String id;//订单编号
	private double money;
	private String receiverAddress;
	private String receiverName;
	private String receiverEmail;
	//订单支付时间貌似数据库会自己生成的，先试试看
	private User user;//该订单所属的用户
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();//这张订单中购买的商品及其数量构成的List
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
