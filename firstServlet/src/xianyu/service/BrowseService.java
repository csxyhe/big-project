package xianyu.service;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import xianyu.Dao.BrowseDao;
import xianyu.Dao.operationDao;
public class BrowseService {
	private BrowseDao bd = new BrowseDao();
	private operationDao od = new operationDao();
	//添加一条浏览日志记录
	public void addBrowse(int user_id,int business_id,String product_id,String begintime,String endtime,int during) {
		try {
			bd.addBrowse(user_id, business_id,product_id,begintime,endtime,during);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//读出所有浏览过该销售人员的商品的用户浏览日志
	public List<Object[]> returnall(int b_id) throws SQLException{
		List<Object[]> ls = new ArrayList<Object[]>();
		ls = bd.returnall(b_id);
		return ls;
	}
	//管理员根据日期范围读出用户浏览日志
	public List<Object[]> returnmonth_all(String year, String month, int op_id, String role, String op_name, String op_content, String ip)
			throws SQLException{
		od.addOperation(op_id, role, op_name, op_content, ip);
		return bd.returnmonth_all(year,month);
	}
	//推荐系统用，返回该用户近一个月内的浏览的pid
	public List<Object[]> returnMB_recommend(int userid)throws SQLException{
		return bd.returnMB_recommend(userid);
	}
}
