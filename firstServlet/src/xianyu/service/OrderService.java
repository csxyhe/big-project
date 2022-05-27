//�ṩ����order�ķ���ӿ�
package xianyu.service;
import xianyu.domain.Order;
import xianyu.Dao.OrderItemDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xianyu.Dao.OrderDao;
import xianyu.Dao.ProductDao;
import xianyu.Dao.operationDao;
import xianyu.utils.DataSourceUtils;
import xianyu.utils.MailUtils;
public class OrderService {
	private OrderDao od = new OrderDao();
	private OrderItemDao oid = new OrderItemDao();
	private ProductDao pd = new ProductDao();
	public void addOrder(Order order) {
		try {
			//Ҫʹ�ûع����ƣ���ʹ���Զ����������
			// 1.��������
			DataSourceUtils.startTransaction();
			// 2.���һϵ�е�JDBC����
			// 2.1.�Ѷ�����Ϣ���浽������orders
			od.addOrder(order);
			// 2.2.�򶩵���Ŀ��oderitems���������
			oid.addOrderItem(order);
			// 2.3.�޸�products���и���Ʒ�Ŀ������
			pd.changeProductNum(order);
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				// �����쳣����ع�����
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				//�����������ͷ�����
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//��Ʒ����ͳ��
	public List<Object[]> calStatistics(String year, String month,int user_id){
		List<Object[]> statis = new ArrayList<Object[]>();
		try {
			statis = oid.calStatistics(year,month,user_id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statis;
	}
	public List<Object[]> findBuyRecord(int myuid, String user_id,String gender, String year,String month) throws SQLException{
		return oid.findBuyRecord(myuid,user_id, gender, year, month);
	}
	//���ض��������ѯ�Ľ��
	public List<Object[]> findOrderByManyCondition(String user_id,String gender, String year,String month){
		List<Object[]> ls = new ArrayList<Object[]>();
		try {
			ls = oid.findOrderByManyCondition(user_id, gender, year, month);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	}
	//���ظ�������Ա������Ʒ��������Ϣ
	public List<Object[]> findDetailsByBid_Date(String b_id,String year,String month) throws SQLException{
		List<Object[]> psperb = new ArrayList<Object[]>();
		psperb = oid.findDetailsByBid_Date(b_id,year,month);
		return psperb;
	}
	//�������뷵�ط���Ҫ��ĵ�Ʒ��ͳ����Ϣ������ID�����ƣ����ۣ���棬ĳ������
	public List<Object[]> statisticsProSales(String p_id,String category, String year, String month, String minprice, String maxprice, String month_minnum,String month_maxnum)
		throws SQLException{
		List<Object[]> psales = new ArrayList<Object[]>();
		psales = oid.statisticsProSales(p_id, category, year, month, minprice, maxprice, month_minnum, month_maxnum);
		return psales;
	}
	//�������뷵����������ͳ����Ϣ������������ƣ������������������۶�
	public List<Object[]> statisticsCategorySales(String year, String month) throws SQLException{
		List<Object[]> csales = new ArrayList<Object[]>();
		csales = oid.statisticsCategorySales(year, month);
		return csales;
	}
	//���ع�ȥ��������ݣ�����������Ԥ��
	public List<Integer> predictSales(String p_id)throws SQLException{
		return oid.predictSales(p_id);
	}
	//���ظ����û����û���������
	public List<Object[]> getUserFigureData(int op_id,String role,String op_name,String ip)throws SQLException{
		operationDao oped = new operationDao();
		oped.addOperation(op_id, role, op_name, "", ip);
		return oid.getUserFigureData();
	}
	//���ؽ�һ���ڶ���
	public List<Object[]> searchMonthOrderRecord(int userid)throws SQLException{
		return od.searchMonthOrderRecord(userid);
	}
	//���ظ��û����ж���
	public List<Object[]> searchAllOrderRecord(int userid) throws SQLException{
		return od.searchAllOrderRecord(userid);
	}
	//��������Ķ����ţ����ظö����Ĺ�������
	public List<Object[]> returnOIDetails(String orderid) throws SQLException{
		return oid.returnOIDetails(orderid);
		
	}
	//�Ƽ�ϵͳ��
	public List<Object[]> returnUPB_recommend_need()throws SQLException{
		return oid.returnUPB_recommend_need();
	}
	//�����쳣ͳ��
	public List<Object[]> searchAbnormality() throws SQLException{
		return oid.searchAbnormality();
		
	}
}
