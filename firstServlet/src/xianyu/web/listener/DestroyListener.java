//// 增加一个监听器，用于在tomcat服务器关闭时回收数据库连接资源和注册驱动资源
package xianyu.web.listener;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class DestroyListener implements ServletContextListener {
	// ServletContext对象一个web应用只有一个，当tomcat服务器关闭or移除该web应用时被销毁
    public void contextInitialized(ServletContextEvent sce) {
    }
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver d = null;
        while (drivers.hasMoreElements()) {
	        try {
	               d = drivers.nextElement();
	               DriverManager.deregisterDriver(d);// 释放注册驱动
	        } catch (SQLException e) {
	              e.printStackTrace();
	        }
        }
        AbandonedConnectionCleanupThread.checkedShutdown();// 释放数据库连接线程
     }
}
