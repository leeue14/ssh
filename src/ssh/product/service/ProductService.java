package ssh.product.service;

import java.util.List;

import ssh.product.domain.Product;
import ssh.web.utils.PageBean;

public interface ProductService {

	//查询热门商品
	public List<Product> findHot();

	//查询最新商品
	public List<Product> findNew();
	
	//分类页面显示商品的方法
	public PageBean<Product> findByPage(Integer cid, Integer page);

	//查询某个商品详情
	public Product findByPid(Integer pid);

	//根据二级分类的ID，查询商品
	public PageBean<Product> findByCsid(Integer csid ,Integer page);

	//后台：查询所有商品（分页）
	public PageBean<Product> findByPage(Integer page);

	//保存商品
	public void save(Product product);

	//删除商品
	public void delete(Product product);
	
	//付款成功后 修改商品数量
	public void updateCount(Integer pid,Integer count);
}
