package ssh.category.dao;

import java.util.List;

import ssh.category.domain.Category;
import ssh.web.base.BaseDao;

/**
 * 一级分类Dao层代码
 */
public interface CategoryDao extends BaseDao<Category> {

	//查询所有一级分类

	//保存一级分类

	//删除

	//修改前根据ID查询
	public Category findByCid(Integer cid);

	//后台修改

}
 