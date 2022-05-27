//提供对所有人的登入登出日志进行查询或修改的服务接口
package xianyu.service;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import xianyu.Dao.LoginoutDao;
public class LoginoutService {
	//添加一条登入登出记录
	public void addLoginout(int userid,Timestamp logintime,Timestamp logouttime,String ip,String role,String province) 
			throws SQLException {
		LoginoutDao ld = new LoginoutDao();
		ld.addLoginout(userid, logintime, logouttime, ip, role, province);
	}
	//根据输入时间范围查找所有满足条件的登入登出日志
	public List<Object[]> getLoginout(String year, String month, String ip, int userid, String role, String operationType) throws SQLException{
		LoginoutDao ld = new LoginoutDao();
		return ld.getLoginout(year, month, ip, userid, role, operationType);
	}
}
