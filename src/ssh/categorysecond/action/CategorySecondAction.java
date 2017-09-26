package ssh.categorysecond.action;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.mapping.Set;

import ssh.category.domain.Category;
import ssh.category.service.CategoryServiceImpl;
import ssh.categorysecond.domain.CategorySecond;
import ssh.categorysecond.service.CategorySecondServiceImpl;
import ssh.web.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public interface CategorySecondAction {


	//查询二级分类带有分页
	public String adminFindAll();

	//后台：二级分类，跳转到页面的方法
	public String addPage();

	//后台：二级分类的保存
	public String save();
}
