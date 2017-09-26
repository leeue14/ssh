 package ssh.categorysecond.service;

import java.util.List;
import ssh.categorysecond.domain.CategorySecond;
import ssh.web.utils.PageBean;

public interface CategorySecondService {

	//查询全部二级分类
	public PageBean<CategorySecond> findByPage(Integer page);

	//二级分类
	public void save(CategorySecond categorySecond);
	
	//后台：跳转到添加页面
	public List<CategorySecond> findAll();
}
