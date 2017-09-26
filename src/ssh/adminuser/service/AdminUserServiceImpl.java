package ssh.adminuser.service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import ssh.adminuser.dao.AdminUserDao;
import ssh.adminuser.dao.AdminUserDaoImpl;
import ssh.adminuser.domain.AdminUser;

@Transactional
public class AdminUserServiceImpl implements AdminUserService {

	//注入dao
	@Resource(name="adminUserDao")
	private AdminUserDao adminUserDao;
	
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}

}
