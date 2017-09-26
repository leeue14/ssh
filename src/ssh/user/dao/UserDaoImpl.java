package ssh.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ssh.user.domain.User;
import ssh.web.base.BaseDaoImpl;

/**
 * 用户模块Dao层代码
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

 	//DAO层保存用户的代码	 :baseDao
	
	// DAO层根据激活码查询用户
	public User findByCode(String code) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from User where code = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, code);

		List<User> list = query.list();
		if(list.size()!=0){
			return list.get(0);
		} else {
			return null;
		}

	}

	//DAO层修改用户的方法		：baseDao

	
	// DAO层的登录方法
	public User login(User user) {

		Session session = sessionFactory.getCurrentSession();

		String hql = "from User where username = ? and password = ? and state = ?";

		Query query = session.createQuery(hql);
		query.setParameter(0, user.getUsername());
		query.setParameter(1, user.getPassword());
		query.setParameter(2, 1);

		List<User> list = query.list();

		if(list.size()!=0){
			return list.get(0);
		} else {
			return null;
		}

	}

	public User findByUserName(String username) {

		Session session = sessionFactory.getCurrentSession();

		String hql = "from User where username = ?";

		Query query = session.createQuery(hql);
		query.setParameter(0, username);


		List<User> list = query.list();
		
		if( list != null && !list.isEmpty() ){
			return list.get(0);
		} else {
			return null;
		}
	
	}
}
