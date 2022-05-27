// ���ڻ�ȡ�û�����ʵIP��ַ
//�����������Ա�IP�ӿڲ�ѯ�û����ڵ�ַ
package xianyu.utils;

import javax.servlet.http.HttpServletRequest;
public class GetRealIPUtils {
	public static String getIP(HttpServletRequest request) {
	    String ip = request.getHeader("x-forwarded-for");
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    	ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    if (ip.equals("0:0:0:0:0:0:0:1")||ip.equals("127.0.0.1")) {
	        ip = "local";
	    }
	    return ip;
	}
}

