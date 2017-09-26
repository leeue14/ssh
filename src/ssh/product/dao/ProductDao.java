package ssh.product.dao;

import java.util.List;

import ssh.product.domain.Product;
import ssh.web.base.BaseDao;

public interface ProductDao extends BaseDao<Product> {

	//查询热门商品，只显示10个
	public List<Product> findHot();
	
	//查询最新商品，只显示10个
	public List<Product> findNew();

	// 统计某个分类下的商品的总数:
	public Integer findCountByCid(Integer cid);

	//显示商品集合
	public List<Product> findByPage(Integer cid, int begin, int limit);

	//根据ID查询商品
	public Product findByPid(Integer pid);
	
	//根据二级分类ID查询商品
	public Integer findCountByCsid(Integer csid);

	public List<Product> findByPageCsid(Integer csid, int begin, int limit);

	//后台：二级分类商品总记录数
	public Integer findCount();

	//后台：二级分类商品每页显示记录
	public List<Product> findByPage(Integer begin, Integer limit);

	
	
	//保存商品

	//根据ID删除商品
	
	
	//付款成功后 修改商品数量
	public void updateCount(Integer pid, Integer count);
}
