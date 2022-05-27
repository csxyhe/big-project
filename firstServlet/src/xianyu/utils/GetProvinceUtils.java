//ͨ����õ�IP��ַ����ѯ�û�����ʡ�ݣ����ں������û�����
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
					if (code==200) {//���ӳɹ�����Ӧ��Ϊ200
						InputStreamReader  inStream = new InputStreamReader(conn.getInputStream(),"UTF-8");
						BufferedReader buffer=new BufferedReader(inStream);
						while((inputline=buffer.readLine())!=null){
							info+=inputline;
						}
						String subInfo = info.substring(info.indexOf("{"), info.indexOf("}")+1);
						JSONObject jsonob = JSONObject.fromObject(subInfo);
						province = jsonob.getString("province");//��ת���˵�Unicode�������»���UTF-8
					}

				} catch (IOException e) {}
			}
		}
		return province;
	}
}
