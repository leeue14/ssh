package ssh.categorysecond.action;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.mapping.Set;

import ssh.category.domain.Category;
import ssh.category.service.CategoryService;
import ssh.category.service.CategoryServiceImpl;
import ssh.categorysecond.domain.CategorySecond;
import ssh.categorysecond.service.CategorySecondService;
import ssh.categorysecond.service.CategorySecondServiceImpl;
import ssh.web.base.BaseAction;
import ssh.web.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategorySecondActionImpl extends BaseAction<CategorySecond> implements CategorySecondAction {

	//接受页数参数
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}

	//接收cid--封装
	private Integer cid;
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	//模型驱动

	
	//查询二级分类带有分页
	public String adminFindAll(){
		PageBean<CategorySecond> pageBean= categorySecondService.findByPage(page);
		set("pageBean", pageBean);
		return "adminFindAllSuccess";
	}

	//后台：二级分类，跳转到页面的方法
	public String addPage(){
		List<Category> cList = categoryService.findAll();
		//压栈
		set("cList", cList);
		return "addPageSuccess";
	}

	//后台：二级分类的保存
	public String save(){
		//一级分类的对象--得到cid
		Category category = new Category();
		category.setCid(cid);
		
		//二级分类的对象,并把一级对象	
		this.getModel().setCategory(category);

		//调用service--
		categorySecondService.save(this.getModel());
		return "saveSuccess";
	}


}
