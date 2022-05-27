//通过获得的IP地址来查询用户所在省份，用于后续做用户画像
package xianyu.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import net.sf.json.JSONObject;
public class GetProvinceUtils {
	public static String getProvince(String ip){
		String province = "--";
		if (ip.equals("local")) {
		}else {
			while(province.equals("--")) {
				try {

					String path = "https://ip.useragentinfo.com/json?" + ip;
					String inputline="";
					String info="";
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					conn.setReadTimeout(10*1000);
					conn.setRequestMethod("GET");
					int code =conn.getResponseCode();
					if (code==200) {//连接成功，响应码为200
						InputStreamReader  inStream = new InputStreamReader(conn.getInputStream(),"UTF-8");
						BufferedReader buffer=new BufferedReader(inStream);
						while((inputline=buffer.readLine())!=null){
							info+=inputline;
						}
						String subInfo = info.substring(info.indexOf("{"), info.indexOf("}")+1);
						JSONObject jsonob = JSONObject.fromObject(subInfo);
						province = jsonob.getString("province");//将转义了的Unicode编码重新换成UTF-8
					}

				} catch (IOException e) {}
			}
		}
		return province;
	}
}
