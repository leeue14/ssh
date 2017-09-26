package ssh.adminuser.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ssh.adminuser.domain.AdminUser;
import ssh.web.base.BaseDaoImpl;

public class AdminUserDaoImpl extends BaseDaoImpl<AdminUser> implements AdminUserDao {


	public AdminUser login(AdminUser adminUser) {
		//获取与当前线程绑定的session  
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from AdminUser where username = ? and password = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, adminUser.getUsername());
		query.setParameter(1, adminUser.getPassword());
		
		List<AdminUser> list = query.list();
		
		if (list != null && list.size() != 0) {
			return list.get(0);
		}else {
			return null;
		}
		
	}

}
