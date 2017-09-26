package ssh.category.dao;

import ssh.category.domain.Category;
import ssh.web.base.BaseDaoImpl;

/**
 * 一级分类Dao层代码
 */
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {

	//查询所有一级分类

	//保存一级分类

	//删除

	//修改前根据ID查询
	public Category findByCid(Integer cid) {
		return sessionFactory.getCurrentSession().get(Category.class, cid);
	}

	//后台修改

}
 