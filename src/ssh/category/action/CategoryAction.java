package ssh.category.action;

public interface CategoryAction {

	//后台查询一级分类
	public String adminFindAll();

	//后台保存一级分类
	public String save();

	//后台：删除一级分类
	public String delete();

	//后台修改一级分类（查询一级分类）
	public String edit();

	//后台：修改一级分类
	public String update();

}
