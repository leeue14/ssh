package ssh.user.dao;

import ssh.user.domain.User;
import ssh.web.base.BaseDao;

/**
 * 用户模块Dao层代码
 */
public interface UserDao extends BaseDao<User> {
	
	//DAO层保存用户的代码	 

	// DAO层根据激活码查询用户
	public User findByCode(String code);

	//DAO层修改用户的方法

	// DAO层的登录方法
	public User login(User user);
	
	//通过用户名查找
	public User findByUserName(String username);
}
