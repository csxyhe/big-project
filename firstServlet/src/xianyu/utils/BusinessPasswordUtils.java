package xianyu.utils;
// �������������Ա��¼���루���ǵ�ԭ�������룩
public class BusinessPasswordUtils {
	// ����10λ��¼���ÿ���ַ�����ΪӢ��Сд��ĸ�������֣�
	public static String BusinessPassword() {
		// ������֤����ַ���
		String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
		String activeCode = null;
		for (int i=0;i<12;i++) {
			int rand = (int)(Math.random()*36);
			if (i==0) {
				activeCode = String.valueOf(chars.charAt(rand)); // String.valueOf()������һ��char��string��ת��
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
