// 生成32位的唯一标识符，生成的标识符是有 '-' 作为分隔符的
package xianyu.utils;

import java.util.UUID;
public class IdUtils {
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
