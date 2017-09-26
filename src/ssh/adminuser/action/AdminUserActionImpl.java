package ssh.adminuser.action;

import ssh.adminuser.domain.AdminUser;
import ssh.web.base.BaseAction;

public class AdminUserActionImpl extends BaseAction<AdminUser> implements AdminUserAction {

	//后台登录的方法
	public String login(){
		AdminUser existAdminUser = adminUserService.login(this.getModel());
		if (existAdminUser == null) {
			//登录失败
			this.addActionError("用户名或密码错误");
			return LOGIN;
		}else {
			//登录成功		存入session  用的IOC方式
			putSession("existAdminUser", existAdminUser);
			return "loginSuccess";
		}

	}



}
