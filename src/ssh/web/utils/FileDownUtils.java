package ssh.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownUtils {
	//适应各种浏览器  
//	public static String adaptiveBrowser(HttpServletRequest request, String filename)  
//	throws UnsupportedEncodingException {  
//
//		String agent= request.getHeader("user-agent");  
//		if (agent.contains("MSIE")) {  
//			// IE浏览器  
//			filename = URLEncoder.encode(filename, "utf-8");  
//
//		}else if(agent.contains("Firefox")){  
//			// 火狐浏览器  
//			BASE64Encoder base64Encoder= new BASE64Encoder();  
//			filename = "=?utf-8?B?"  
//					+base64Encoder.encode(filename.getBytes("utf-8"))  
//					+"?=";  
//		}else{  
//			// 其它浏览器  
//			filename = URLEncoder.encode(filename, "utf-8");  
//		}  
//		return filename;  
//	}  


	public static void foreverDown(HttpServletResponse response, String filename){  
		// 下载注意事项2--永远是下载  
		response.setHeader("content-disposition","attachment;filename="  
				+filename);  
	}  

	public static void downFile(HttpServletResponse response, File file)  
			throws FileNotFoundException,IOException {  
		FileInputStream fis= new FileInputStream(file);// 读取要下载文件的内容  
		OutputStream os = response.getOutputStream(); // 将要下载的文件内容通过输出流写回到浏览器端.  
		int len = -1;  
		byte[] b = new byte[1024 * 100];  

		while ((len = fis.read(b)) != -1) {  
			os.write(b, 0, len);  
			os.flush();  
		}  
		os.close();  
		fis.close();  
	}  
}  

