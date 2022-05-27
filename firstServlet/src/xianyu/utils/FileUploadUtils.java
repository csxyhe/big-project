package xianyu.utils;
import java.util.UUID;

public class FileUploadUtils {
	public static String subFileName(String allname) {
		// 截取真实的文件名
		int index = allname.lastIndexOf("\\");
		if(index == -1) {
			//本身就只有文件名
			return allname;
		}else {
			return allname.substring(index+1);
		}
	}
	
	public static String generateRandomFileName(String filename) {
		// 用于生成在临时文件夹中存储的随机的唯一的文件名
		int index = filename.lastIndexOf(".");
		if (index == -1) {
			return UUID.randomUUID().toString();
		}else {
			String exName = filename.substring(index);// 获取该文件的扩展名（扩展名不是每个文件都有的）
			return UUID.randomUUID().toString() + exName;
		}
	}
	
	public static String generateRandomDir(String uuidFileName) {
		// 获得hashcode生成二级目录
		int hashCode = uuidFileName.hashCode();
		// 一级目录
		int d1 = hashCode & 0xf;
		// 二级目录
		int d2 = (hashCode >> 4) & 0xf;
		return "/" + d1 + "/" + d2;
	}
}
