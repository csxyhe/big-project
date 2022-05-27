// �������ݿ����ӳؼ�����mysql���ݿ⽨������
package xianyu.utils;
import javax.sql.DataSource;
import java.sql.*;
import org.apache.commons.dbcp.BasicDataSource;
/*
 * ��������Ĳ�������
 * Connection conn = DataSourceUtils.getConnection();
 * try{
 * 		DataSourceUtils.startTransaction();
 * 		ִ��һϵ��jdbc����
 * 		DataSourceUtils.releaseAndCloseConnection();
 * } catch (Exception e){
 * 		DataSourceUtils.rollback();// �����쳣����ع�����
 * }
 */
public class DataSourceUtils {
	private static DataSource ds = null;
	static {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		bds.setUrl("jdbc:mysql://localhost:3306/bigjob");
		bds.setUsername("root");
		bds.setPassword("hxy010217");
		bds.setInitialSize(5);
		bds.setMaxActive(20);
		ds = bds;
	}
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();// ��ÿ�����ݿ��������һ���߳�
	//�Զ�
	public static DataSource getDataSource() {
		return ds;
	}
	
	//�ֶ�
	// ��DBUtils��Ҫ�ֶ���������ʱ�����ø÷������һ������
	public static Connection getConnection() throws SQLException{
		Connection conn = tl.get();// ���䵽�������̸߳տ�ʼΪ��
		if (conn == null) {
			conn = ds.getConnection();
			tl.set(conn);
		}
		return conn;
	}
	// ��������
	public static void startTransaction() throws SQLException{
		Connection conn = getConnection();
		if(conn!=null) {
			conn.setAutoCommit(false);// ����Ϊ�ֶ���������
		}
	}
	// �������񣬲��ͷ��߳���Դ
	public static void releaseAndCloseConnection() throws SQLException{
		Connection conn = getConnection();
		if(conn!=null) {
			conn.commit();
			tl.remove();
			conn.close();
		}
	}
	// ����ع�
	public static void rollback() throws SQLException{
		Connection conn = getConnection();
		if(conn!=null) {
			conn.rollback();
		}
	}
}
