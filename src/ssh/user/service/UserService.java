package ssh.user.service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import ssh.user.dao.UserDaoImpl;
import ssh.user.domain.User;
import ssh.web.utils.MailUtils;
import ssh.web.utils.UUIDUtils;

/**
 * 用户模块:业务层代码
 */
public interface UserService {

	//注册
	public void regist(User user);

	//注册用户激活
	public User findByCode(String code);

	// 业务层修改用户的方法
	public void update(User existUser);

	// 业务层登录的方法
	public User login(User user);

	public User findByUserName(String username);
}
