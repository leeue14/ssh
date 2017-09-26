package ssh.user.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import ssh.user.domain.User;
import ssh.user.service.UserServiceImpl;
import ssh.web.utils.MyMD5;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;


/**
 * 用户模块的Action
 */
public interface UserAction {
	//跳转到注册页面的方法
	public String registPage();

	//前台：注册用户的方法
	public String regist();

	// 前台:激活用户的方法:
	public String active();

	//前台跳转到登录页面的方法
	public String loginPage();

	// 前台:登录功能:
	public String login();
	
	// 前台:注册AJAX校验用户名.
	public String checkUserName() throws IOException;
	
	//用户退出的方法(删除cookies)
	public String quit();
}
