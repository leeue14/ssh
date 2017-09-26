package ssh.categorysecond.dao;

import java.util.List;

import ssh.categorysecond.domain.CategorySecond;
import ssh.web.base.BaseDao;

public interface CategorySecondDao extends BaseDao<CategorySecond> {

	//查询二级分页的记录数
	public Integer findCount();

	public List<CategorySecond> findByPage(Integer begin, Integer limit);

	//二级分类保存

	//二级分类查询所有
}
