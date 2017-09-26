package ssh.product.action;

import java.io.IOException;

public interface ProductAction {
	
	//查询商品的方法
	public String findByCid();

	//查询商品详情
	public String findByPid();

	//查询二级分类的商品
	public String findByCsid();

	//后台：查询所有商品的方法
	public String adminFindAll();

	// 跳转到添加页面
	public String addPage();

	//保存商品：同时上传图片
	public String save() throws IOException;

	//删除商品
	public String delete();
}
