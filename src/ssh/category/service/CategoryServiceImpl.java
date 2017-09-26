package ssh.category.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import ssh.category.dao.CategoryDao;
import ssh.category.dao.CategoryDaoImpl;
import ssh.category.domain.Category;
import ssh.web.utils.MailUtils;
import ssh.web.utils.UUIDUtils;


@Transactional
public class CategoryServiceImpl implements CategoryService {

	//注入dao--注解方式
	@Resource(name="categoryDao")
	private CategoryDao categoryDao;

	//业务层查询一级分类
	public List<Category> findAll() {
		
		return categoryDao.findAll();
	}

	//保存一级分类
	public void save(Category category) {
		categoryDao.save(category);
	}

	//删除
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	//修改前根据ID查询
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
		
	}

	//后台：修改一级分类
	public void update(Category category) {

		categoryDao.update(category);
	}

	
}
