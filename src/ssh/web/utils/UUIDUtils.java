package ssh.web.utils;

import java.util.UUID;

/**
 * 生成激活码
 */
public class UUIDUtils {
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
