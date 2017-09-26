package ssh.category.action;

import java.util.List;

import ssh.category.domain.Category;
import ssh.web.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

public class CategoryActionImpl  extends BaseAction<Category> implements CategoryAction {

	private static final long serialVersionUID = 1L;

	//后台查询一级分类
	public String adminFindAll(){
		List<Category> cList = categoryService.findAll();
		//压入值栈
		set("cList", cList);

		return "adminFindAllScccess";
	}

	//后台保存一级分类
	public String save(){
		categoryService.save(this.getModel());
		return "saveSuccess";
	}

	//后台：删除一级分类
	public String delete(){
		categoryService.delete(this.getModel());
		return "deleteSuccess";
	}

	//后台修改一级分类（查询一级分类）
	public String edit(){
		//先查询
		System.out.println(this.getModel());
	    Category category = categoryService.findByCid(this.getModel().getCid());
	    //把获取到的数据压栈
	    push(category);
		return "editSuccess";
	}

	//后台：修改一级分类
	public String update(){
		categoryService.update(this.getModel());
		return "updateSuccess";
	}


}
