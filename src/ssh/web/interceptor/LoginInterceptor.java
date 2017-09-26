package ssh.web.interceptor;

import org.apache.struts2.ServletActionContext;

import ssh.adminuser.domain.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{

	//后台拦截
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		
		AdminUser existAdminUser = (AdminUser) ServletActionContext
				.getRequest().getSession().getAttribute("existAdminUser");
		if(existAdminUser != null){
			//放行
			return actionInvocation.invoke();
		}else{
			ActionSupport action = (ActionSupport) actionInvocation.getAction();
			action.addActionError("您还没有登录!");
			return "login";
		}
		
	}

}
