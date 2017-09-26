package ssh.category.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import ssh.category.dao.CategoryDaoImpl;
import ssh.category.domain.Category;
import ssh.web.utils.MailUtils;
import ssh.web.utils.UUIDUtils;

public interface CategoryService {

	//业务层查询一级分类
	public List<Category> findAll();
	
	//保存一级分类
	public void save(Category category);

	//删除
	public void delete(Category category);

	//修改前根据ID查询
	public Category findByCid(Integer cid);

	//后台：修改一级分类
	public void update(Category category);
}
