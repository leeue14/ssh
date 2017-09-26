package ssh.adminuser.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ssh.adminuser.domain.AdminUser;
import ssh.web.base.BaseDao;

public interface AdminUserDao extends BaseDao<AdminUser> {

	/**
	 * 后台：查询用户名密码
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser);

}
