// 用于获取用户的真实IP地址
//后期再利用淘宝IP接口查询用户所在地址
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

