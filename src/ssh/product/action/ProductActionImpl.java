package ssh.product.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import ssh.category.domain.Category;
import ssh.category.service.*;
import ssh.categorysecond.domain.CategorySecond;
import ssh.categorysecond.service.*;
import ssh.product.domain.Product;
import ssh.product.service.*;
import ssh.web.base.BaseAction;
import ssh.web.utils.FileDownUtils;
import ssh.web.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ProductActionImpl  extends BaseAction<Product> implements ProductAction {
	//注入一级分类的service

	//注入二级分类service

	//注入ProductService

	/////////////////////////////////////////////////////////////////////////	
	//接受cid--struts
	private Integer cid ;
	//接收二级分类的id
	private Integer csid;
	//接受商品pid
	private Integer pid;
	//接收当前页数
	private Integer page;
	//分页商品的显示--ognl
	private PageBean<Product> pageBean;	
	// 模型驱动类

	// 文件上传需要的三个属性:
	private File upload;	// 上传文件
	private String uploadContentType; // 上传文件的MIME类型
	private String uploadFileName; // 上传文件名称
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCid() {
		return cid;
	}

	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public PageBean<Product> getPageBean() {
		return pageBean;
	}

	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	//查询商品的方法
	public String findByCid(){
		//查询分类
		//查询所有一级分类
		List<Category> categoryList =categoryService.findAll();
		// 获得值栈:
		set("categoryList", categoryList);

		//查询商品
		pageBean = productService.findByPage(cid , page);

		return "findByCidSuccess";
	}

	//查询商品详情
	public String findByPid(){
		// 查询所有一级分类:
		List<Category> categoryList = categoryService.findAll();
		// 获得值栈:
		set("categoryList", categoryList);

		Product product = productService.findByPid(this.getModel().getPid());
		push(product);
		return "findByPidSuccess";
	}	


	//查询二级分类的商品
	public String findByCsid(){
		// 查询所有一级分类:
		List<Category> categoryList = categoryService.findAll();
		// 获得值栈:
		set("categoryList", categoryList);

		pageBean = productService.findByCsid(csid ,page);
		return "findByCsidSuccess";
	}	

	//后台：查询所有商品的方法
	public String adminFindAll() {
		pageBean = productService.findByPage(page);
		return "adminFindAllSuccess";
	}

	// 跳转到添加页面
	public String addPage(){
		// 查询所有的二级分类 :
		List<CategorySecond> csList = categorySecondService.findAll();
		set("csList", csList);
		return "addPageSuccess";
	}

	//保存商品：同时上传图片
	public String save() throws IOException{
		// 文件上传的操作:
		// 获得上传的路径:
		String path = ServletActionContext.getServletContext().getRealPath("/products");
		String realPath = path+"/"+csid+"/"+uploadFileName;
		File diskFile = new File(realPath);
		// 文件上传:
		FileUtils.copyFile(upload, diskFile);

		// 保存到数据库:
		// 设置二级分类
		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCsid(csid);
		this.getModel().setCategorySecond(categorySecond);
		// 设置时间:
		this.getModel().setPdate(new Date());
		// 设置图片上传路径:
		this.getModel().setImage("products/"+csid+"/"+uploadFileName);

		// 调用Serviec保存商品:
		productService.save(this.getModel());
		
		return "saveSuccess";
	}

	//删除商品
	public String delete() {
		productService.delete(this.getModel());
		return "deleteSuccess";
	}
}
