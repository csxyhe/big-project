//�ṩ�������˵ĵ���ǳ���־���в�ѯ���޸ĵķ���ӿ�
package xianyu.service;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import xianyu.Dao.LoginoutDao;
public class LoginoutService {
	//���һ������ǳ���¼
	public void addLoginout(int userid,Timestamp logintime,Timestamp logouttime,String ip,String role,String province) 
			throws SQLException {
		LoginoutDao ld = new LoginoutDao();
		ld.addLoginout(userid, logintime, logouttime, ip, role, province);
	}
	//��������ʱ�䷶Χ�����������������ĵ���ǳ���־
	public List<Object[]> getLoginout(String year, String month, String ip, int userid, String role, String operationType) throws SQLException{
		LoginoutDao ld = new LoginoutDao();
		return ld.getLoginout(year, month, ip, userid, role, operationType);
	}
}
