package xianyu.service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xianyu.Dao.ApplicationDao;
public class ApplicationService {
	private ApplicationDao ad= new ApplicationDao();
	//将申请人的用户ID写入applications表中
	public void addApplication(int id) throws SQLException{
		ad.addApplication(id);
	}
	
	//读出所有请求成为销售人员的用户ID
	public List<Object[]> returnall() throws SQLException{
		List<Object[]> ls = new ArrayList<Object[]>();
		ls = ad.returnall();
		return ls;
	}
	//处理完一条记录后，在application表中删除它
	public void deleteid(int userid) throws SQLException{
		ad.deleteid(userid);
	}
}
