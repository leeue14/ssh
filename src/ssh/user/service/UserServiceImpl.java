package ssh.user.service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import ssh.user.dao.UserDao;
import ssh.user.dao.UserDaoImpl;
import ssh.user.domain.User;
import ssh.web.utils.MailUtils;
import ssh.web.utils.UUIDUtils;

/**
 * 用户模块:业务层代码
 */
@Transactional
public class UserServiceImpl implements UserService{
	// 注入Dao
	@Resource(name="userDao")
	private UserDao userDao;

	//注册
	public void regist(User user) {
		// 保存用户:
		user.setState(0);		// 0 未激活  1已经激活.
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();//生成激活码
		user.setCode(code);
		userDao.save(user);
		// 发送邮件:
		try {
			MailUtils.sendMail(user.getEmail(), code);
			System.out.println("邮箱执行了");
		} catch (Exception e) {
			System.out.println("邮箱异常");
			e.printStackTrace();
		}
	}


	//注册用户激活
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	// 业务层修改用户的方法
	public void update(User existUser) {
		userDao.update(existUser);
	}

	// 业务层登录的方法
	public User login(User user) {
		return userDao.login(user);
	}

	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}
}
