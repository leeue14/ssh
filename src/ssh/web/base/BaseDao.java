package ssh.web.base;

import java.util.List;

public interface BaseDao<T> {

	//添加用户
	public void save(T t);
	//删除用户
	public void delete(T t);
	//编辑用户
	public void update(T t);
	//添加或保存用户
	public void saveOrUpdate(T t);
	//无条件查询所有用户
	public List<T> findAll();
	//通过Id查询用户	String,Int等基本数据类型，都是java.io.Serializable的子类
	public T findById(java.io.Serializable id);

}
