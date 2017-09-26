 package ssh.categorysecond.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import ssh.category.domain.Category;
import ssh.categorysecond.dao.CategorySecondDao;
import ssh.categorysecond.dao.CategorySecondDaoImpl;
import ssh.categorysecond.domain.CategorySecond;
import ssh.web.utils.PageBean;

@Transactional
public class CategorySecondServiceImpl implements CategorySecondService {
	//注入dao
	@Resource(name="categorySecondDao")
	private CategorySecondDao categorySecondDao; 

	//查询全部二级分类
	public PageBean<CategorySecond> findByPage(Integer page) {
		//封装pagebean
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		pageBean.setPage(page);
		Integer limit = 10;
		pageBean.setLimit(limit);
		//总量
		Integer totalCount =  categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//总页数
		Integer totalPage = 0;
		if (totalCount % limit == 0) {
			 totalPage = totalCount/limit;
		} else {
			 totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		Integer begin = (page - 1)*limit;
		List<CategorySecond> csList = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(csList);
		return pageBean;
	}

	//二级分类
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
		
	}
	
	//后台：跳转到添加页面
	public List<CategorySecond> findAll() {
		
		return categorySecondDao.findAll();
	}

}
