package xianyu.utils;
import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
public class MailUtils {
	// �����ʼ�,emailΪ�����ߵ������ַ
	public static void sendMail(String email, String emailMsg, int state) 
		throws AddressException, MessagingException{
		/*
		 * state��ʾ���͵��������ĸ������
		 * state=0��ʾ���͵��ʼ������û�����
		 * state=1��ʾ���͵��ʼ�����ȷ�Ϸ���
		 */
		// 1.����Properties���󣬲������ʼ��������Ļ�����Ϣ
		Properties props = new Properties();
		
		// 1.1����ʹ�õ��ʼ�����Э��ΪSMTP
		props.setProperty("mail.transport.protocol", "SMTP");
		// 1.2����SMTP��������ַ������ѡ���Ѻ��ķ�������
		props.setProperty("mail.host", "smtp.sohu.com");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port", "465");
		// 1.3����SMTP�������Ƿ���Ҫ�û���֤����Ҫ��֤������ΪTrue
		props.setProperty("mail.smtp.auth", "true");
		// 1.4������֤��
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				// �����Ѻ������Ӧ�����˵������˻������룬�����û���֤
				return new PasswordAuthentication("hxy_javaweb", "812XG5K0A3");
			}
		};
		// ʵ�����ʼ��Ựsession
		Session session = Session.getInstance(props, auth);
		// 2.����һ��Message���ö����൱���ʼ�����
		Message msg = new MimeMessage(session);
		// 2.1���÷�����
		msg.setFrom(new InternetAddress("hxy_javaweb@sohu.com"));
		// 2.2���÷��ͷ�ʽ�ͽ����ߣ��ʼ��������ڵ���sendMail()����ʱͨ������email���ݽ���
		msg.setRecipient(RecipientType.TO, new InternetAddress(email));
		// 2.3���÷��͵��ʼ����ʼ�����
		//stage=1ȷ�Ϸ���2�����Ϊ������Ա�ɹ���������µĵ�¼����3����������Ա��ݳɹ�
		if(state==1) {
			msg.setSubject("ȷ�Ϸ���");
		}else if(state==2) {
			msg.setSubject("������Ϣ");
		}else {
			msg.setSubject("������Ϣ");
		}
		
		msg.setContent(emailMsg, "text/html;charset=utf-8");
		// 3.�����ʼ�
		Transport.send(msg);
		
	}
}
