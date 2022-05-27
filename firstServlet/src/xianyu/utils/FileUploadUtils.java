package xianyu.utils;
import java.util.UUID;

public class FileUploadUtils {
	public static String subFileName(String allname) {
		// ��ȡ��ʵ���ļ���
		int index = allname.lastIndexOf("\\");
		if(index == -1) {
			//�����ֻ���ļ���
			return allname;
		}else {
			return allname.substring(index+1);
		}
	}
	
	public static String generateRandomFileName(String filename) {
		// ������������ʱ�ļ����д洢�������Ψһ���ļ���
		int index = filename.lastIndexOf(".");
		if (index == -1) {
			return UUID.randomUUID().toString();
		}else {
			String exName = filename.substring(index);// ��ȡ���ļ�����չ������չ������ÿ���ļ����еģ�
			return UUID.randomUUID().toString() + exName;
		}
	}
	
	public static String generateRandomDir(String uuidFileName) {
		// ���hashcode���ɶ���Ŀ¼
		int hashCode = uuidFileName.hashCode();
		// һ��Ŀ¼
		int d1 = hashCode & 0xf;
		// ����Ŀ¼
		int d2 = (hashCode >> 4) & 0xf;
		return "/" + d1 + "/" + d2;
	}
}
