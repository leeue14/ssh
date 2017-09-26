package ssh.index.action;

import java.util.List;

import javax.annotation.Resource;

import ssh.category.domain.Category;
import ssh.category.service.CategoryService;
import ssh.category.service.CategoryServiceImpl;
import ssh.product.domain.Product;
import ssh.product.service.ProductService;
import ssh.product.service.ProductServiceImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexActionImpl extends ActionSupport implements IndexAction{

	/////////////////////////////////////////
	//注入一级分类的service
	@Resource(name="categoryService")
	private CategoryService categoryService;

	//注入商品的service
	@Resource(name="productService")
	private ProductService productService;

	/////////////////////////////////////////
	//封装热门商品--属性封装
	private List<Product> hotList;

	public List<Product> getHotList() {
		return hotList;
	}

	//封装新商品--属性封装
	private List<Product> newList;

	public List<Product> getNewList() {
		return newList;
	}



	//执行访问首页
	public String index() {
		//查询一级分类
		List<Category> categoryList = categoryService.findAll();
		//存入session
		ActionContext.getContext().getSession().put("categoryList", categoryList);

		//查询热门商品
		hotList = productService.findHot();
		//查询新商品
		newList = productService.findNew();

		return "indexSuccess";
	}

}
