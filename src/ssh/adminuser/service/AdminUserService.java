package ssh.adminuser.service;

import ssh.adminuser.domain.AdminUser;

public interface AdminUserService {

	/**
	 * 后台：登录功能
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser);

}
