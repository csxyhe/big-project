package xianyu.service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xianyu.Dao.ApplicationDao;
public class ApplicationService {
	private ApplicationDao ad= new ApplicationDao();
	//�������˵��û�IDд��applications����
	public void addApplication(int id) throws SQLException{
		ad.addApplication(id);
	}
	
	//�������������Ϊ������Ա���û�ID
	public List<Object[]> returnall() throws SQLException{
		List<Object[]> ls = new ArrayList<Object[]>();
		ls = ad.returnall();
		return ls;
	}
	//������һ����¼����application����ɾ����
	public void deleteid(int userid) throws SQLException{
		ad.deleteid(userid);
	}
}
