package xianyu.service;

import java.sql.SQLException;
import java.util.List;
import xianyu.Dao.operationDao;
public class OperationService {
	private operationDao od = new operationDao();
	//写操作日志后返回操作日志
	public List<Object[]> getOperation(String year, String month, int user_id, String role, String op_name, String op_content, String ip)
		throws SQLException{
		od.addOperation(user_id, role, op_name, op_content, ip);
		return od.getOperation(year, month);
		
	}
}
