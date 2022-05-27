//// ����һ����������������tomcat�������ر�ʱ�������ݿ�������Դ��ע��������Դ
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
	// ServletContext����һ��webӦ��ֻ��һ������tomcat�������ر�or�Ƴ���webӦ��ʱ������
    public void contextInitialized(ServletContextEvent sce) {
    }
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver d = null;
        while (drivers.hasMoreElements()) {
	        try {
	               d = drivers.nextElement();
	               DriverManager.deregisterDriver(d);// �ͷ�ע������
	        } catch (SQLException e) {
	              e.printStackTrace();
	        }
        }
        AbandonedConnectionCleanupThread.checkedShutdown();// �ͷ����ݿ������߳�
     }
}
