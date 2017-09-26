package ssh.user.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import ssh.user.domain.User;
import ssh.user.service.UserService;
import ssh.user.service.UserServiceImpl;
import ssh.web.base.BaseAction;
import ssh.web.utils.MyMD5;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;


/**
 * 用户的Action
 */
public class UserActionImpl extends BaseAction<User> implements UserAction {
	//注入userService

	//////////////////////////////////
	// Struts2中模型驱动使用的类
	
	//验证码接受
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	//////////////////////////////////////////////////////

	//跳转到注册页面的方法
	public String registPage(){
		return "registPageSuccess";
	}

	//前台：注册用户的方法
	@InputConfig(resultName="registInput")
	public String regist(){
		//从session中获得验证码
		//CheckImgAction获得
		String checkcode1 = (String)sessionGet("checkcode");
		//对比
		if (!checkcode.equalsIgnoreCase(checkcode1) || checkcode == null) {
			this.addActionError("验证码错误");
			return "registInput";
		}

		//md5加密
		String sPassword = MyMD5.getMD5(this.getModel().getPassword());
		this.getModel().setPassword(sPassword);

		userService.regist(this.getModel());
		this.addActionMessage("注册成功!请去邮箱激活!");
		return "registSuccess";
	}

	// 前台:激活用户的方法:
	public String active(){
		// 根据激活码查询用户
		User existUser = userService.findByCode(this.getModel().getCode());
		if(existUser != null){
			// 根据激活码查询到这个用户.
			existUser.setState(1);
			// 修改用户的状态
			userService.update(existUser);
			// 添加信息:
			this.addActionMessage("激活成功!请去登录!");
			return "activeSuccess";
		}else{
			this.addActionMessage("激活失败!激活码有误!");
			return "activeSuccess";
		}
	}


	//前台跳转到登录页面的方法
	public String loginPage(){

		return "loginPageSuccess";
	}


	// 前台:登录功能:
	@InputConfig(resultName="loginInput")
	public String login(){

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//从session中获得验证码
		String checkcode2 = (String) sessionGet("checkcode");

		if (!checkcode.equalsIgnoreCase(checkcode2) || checkcode == null) {
			this.addActionError("验证码错误");
			return "loginInput";
		}		

		//md5加密
		String sPassword = MyMD5.getMD5(this.getModel().getPassword());
		this.getModel().setPassword(sPassword);

		User existUser = userService.login(this.getModel());

		if(existUser == null){
			// 登录失败
			this.addActionError("用户名或密码错误或用户未激活!");
			return "loginInput";
		}else{

			//cookie
			String day7 = request.getParameter("day7");

			if ("day7".equals(day7)) {
				//创建cookie
				Cookie cookie1 = new Cookie("username", this.getModel().getUsername());
				Cookie cookie2 = new Cookie("password", this.getModel().getPassword());

				//设置有效时间
				cookie1.setMaxAge(7*24*60*60);
				cookie2.setMaxAge(7*24*60*60);

				//设置关联路径
				cookie1.setPath(request.getContextPath());
				cookie2.setPath(request.getContextPath());

				//发送cookie给浏览器
				response.addCookie(cookie1);
				response.addCookie(cookie2);
			}

			putSession("existUser", existUser);
			return "loginSuccess";
		}

	}



	// 前台:注册AJAX校验用户名.
	public String checkUserName() throws IOException{
		User existUser = userService.findByUserName(this.getModel().getUsername());
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(existUser == null){
			// 用户名可以使用的
			response.getWriter().print("<font color='green'>用户名可以使用</font>");
		} else if (this.getModel().getUsername() == null) {
			response.getWriter().print("<font color='red'>用户名不能为空</font>");
		} else{
			// 用户名已经存在
			response.getWriter().print("<font color='red'>用户名已经存在</font>");
		}
		return NONE;
	}


	//用户退出的方法(删除cookies)
	public String quit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		//获得用户session，销毁session
		request.getSession().invalidate();

		//创建cookie
		Cookie cookie1 = new Cookie("username", null);
		Cookie cookie2 = new Cookie("password", null);

		//设置有效时间
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);

		//设置关联路径
		cookie1.setPath(request.getContextPath());
		cookie2.setPath(request.getContextPath());

		//发送cookie给浏览器
		response.addCookie(cookie1);
		response.addCookie(cookie2);

		return "quitSuccess";
	}

}
