package xianyu.utils;
import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
public class MailUtils {
	// 发送邮件,email为接受者的邮箱地址
	public static void sendMail(String email, String emailMsg, int state) 
		throws AddressException, MessagingException{
		/*
		 * state表示发送的内容是哪个范畴的
		 * state=0表示发送的邮件用于用户激活
		 * state=1表示发送的邮件用于确认发货
		 */
		// 1.创建Properties对象，并设置邮件服务器的基本信息
		Properties props = new Properties();
		
		// 1.1设置使用的邮件传输协议为SMTP
		props.setProperty("mail.transport.protocol", "SMTP");
		// 1.2设置SMTP服务器地址（这里选择搜狐的服务器）
		props.setProperty("mail.host", "smtp.sohu.com");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port", "465");
		// 1.3设置SMTP服务器是否需要用户验证，需要验证则设置为True
		props.setProperty("mail.smtp.auth", "true");
		// 1.4创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				// 设置搜狐邮箱对应发件人的邮箱账户和密码，进行用户验证
				return new PasswordAuthentication("hxy_javaweb", "812XG5K0A3");
			}
		};
		// 实例化邮件会话session
		Session session = Session.getInstance(props, auth);
		// 2.创建一个Message，该对象相当于邮件内容
		Message msg = new MimeMessage(session);
		// 2.1设置发送者
		msg.setFrom(new InternetAddress("hxy_javaweb@sohu.com"));
		// 2.2设置发送方式和接受者，邮件接收者在调用sendMail()方法时通过参数email传递进来
		msg.setRecipient(RecipientType.TO, new InternetAddress(email));
		// 2.3设置发送的邮件的邮件主题
		//stage=1确认发货2申请成为销售人员成功，并获得新的登录口令3撤销销售人员身份成功
		if(state==1) {
			msg.setSubject("确认发货");
		}else if(state==2) {
			msg.setSubject("申请信息");
		}else {
			msg.setSubject("撤销信息");
		}
		
		msg.setContent(emailMsg, "text/html;charset=utf-8");
		// 3.发送邮件
		Transport.send(msg);
		
	}
}
