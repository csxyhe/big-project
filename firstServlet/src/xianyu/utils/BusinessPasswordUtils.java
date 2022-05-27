package xianyu.utils;
// 随机生成销售人员登录密码（覆盖掉原来的密码）
public class BusinessPasswordUtils {
	// 生成10位登录口令（每个字符可以为英文小写字母或者数字）
	public static String BusinessPassword() {
		// 定义验证码的字符表
		String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
		String activeCode = null;
		for (int i=0;i<12;i++) {
			int rand = (int)(Math.random()*36);
			if (i==0) {
				activeCode = String.valueOf(chars.charAt(rand)); // String.valueOf()函数做一个char到string的转换
			}else {
				activeCode += String.valueOf(chars.charAt(rand));
			}
			
		}
		return activeCode;
	}
	public static void main(String[]args) {
		System.out.println(BusinessPassword());
	}
}
