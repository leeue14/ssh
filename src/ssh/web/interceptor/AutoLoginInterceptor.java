package ssh.web.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import ssh.adminuser.domain.AdminUser;
import ssh.user.domain.User;
import ssh.user.service.UserService;
import ssh.user.service.UserServiceImpl;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class AutoLoginInterceptor extends MethodFilterInterceptor{

	@Resource(name="userService")
	private UserService userService;
	
	private static final long serialVersionUID = 1L;

	// 自动登录拦截
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();

		//从request获取所有cookie
		Cookie[] cookies = request.getCookies();
		String username = null;
		String password = null; 


		if (cookies != null) {
			//遍历cookie
			for (Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				String cookieValue = cookie.getValue();

				if ("username".equals(cookieName)) {
					username = cookieValue;
				}

				if ("password".equals(cookieName)) {
					password = cookieValue;
				}
			}
		}

		if (username != null && password != null) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);

			//连接数据库验证用户密码
			User existUser = userService.login(user);
			//登录成功跳转
			if(existUser == null){
				//放行
				return actionInvocation.invoke();
			}else{
				request.getSession().setAttribute("existUser", existUser);
				return actionInvocation.invoke();
			}

		} else {
			//放行
			return actionInvocation.invoke();
		}
	}

}
