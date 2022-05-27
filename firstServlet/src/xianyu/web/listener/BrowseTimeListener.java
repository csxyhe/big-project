package xianyu.web.listener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSessionBindingEvent;
import xianyu.service.BrowseService;
public class BrowseTimeListener implements javax.servlet.http.HttpSessionBindingListener {
	private int userid;
	private int businessid;
	private String productid;
	private Date begintime;
	private Date endtime;
	public void getUserid(int id) {
		this.userid = id;
	}
	public void getBusinessid(int id) {
		this.businessid = id;
	}
	public void getProductid(String id) {
		this.productid = id;
	}
	public void valueBound(HttpSessionBindingEvent arg0)
	{
		this.begintime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begintime = dateFormat.format(this.begintime);
		
	}
	public void valueUnbound(HttpSessionBindingEvent arg0)
	{
		this.endtime = new Date();
		writetime();
	}
	public void writetime()
	{
		int during =(int) (endtime.getTime()-begintime.getTime())/1000;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begintime = dateFormat.format(this.begintime);
		String endtime = dateFormat.format(this.endtime);
		BrowseService bs = new BrowseService();
		bs.addBrowse(this.userid, this.businessid, this.productid, begintime, endtime, during);
	}	
}
