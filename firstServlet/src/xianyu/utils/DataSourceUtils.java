// 运用数据库连接池技术与mysql数据库建立连接
package xianyu.utils;
import javax.sql.DataSource;
import java.sql.*;
import org.apache.commons.dbcp.BasicDataSource;
/*
 * 本身操作的步骤如下
 * Connection conn = DataSourceUtils.getConnection();
 * try{
 * 		DataSourceUtils.startTransaction();
 * 		执行一系列jdbc事务
 * 		DataSourceUtils.releaseAndCloseConnection();
 * } catch (Exception e){
 * 		DataSourceUtils.rollback();// 捕获到异常，则回滚事务
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
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();// 给每个数据库请求分配一个线程
	//自动
	public static DataSource getDataSource() {
		return ds;
	}
	
	//手动
	// 当DBUtils需要手动控制事务时，调用该方法获得一个连接
	public static Connection getConnection() throws SQLException{
		Connection conn = tl.get();// 分配到的连接线程刚开始为空
		if (conn == null) {
			conn = ds.getConnection();
			tl.set(conn);
		}
		return conn;
	}
	// 开启事务
	public static void startTransaction() throws SQLException{
		Connection conn = getConnection();
		if(conn!=null) {
			conn.setAutoCommit(false);// 设置为手动管理事务
		}
	}
	// 结束事务，并释放线程资源
	public static void releaseAndCloseConnection() throws SQLException{
		Connection conn = getConnection();
		if(conn!=null) {
			conn.commit();
			tl.remove();
			conn.close();
		}
	}
	// 事务回滚
	public static void rollback() throws SQLException{
		Connection conn = getConnection();
		if(conn!=null) {
			conn.rollback();
		}
	}
}
