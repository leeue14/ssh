package ssh.product.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import ssh.product.dao.ProductDao;
import ssh.product.domain.Product;
import ssh.web.utils.PageBean;

@Transactional
public class ProductServiceImpl implements ProductService {
	//注入dao
	@Resource(name="productDao")
	private ProductDao productDao;

	//查询热门商品
	public List<Product> findHot() {
		return productDao.findHot();
	}

	//查询最新商品
	public List<Product> findNew() {
		return productDao.findNew();
	}

	//分类页面显示商品的方法
	public PageBean<Product> findByPage(Integer cid, Integer page) {
		PageBean<Product> pageBean =  new PageBean<>();
		//1. 当前页数.
		pageBean.setPage(page);	
		
		//2. 每页显示记录数
		int limit = 12; 
		pageBean.setLimit(limit);
		
		//3. 总记录数
		Integer totalCount = productDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		
		//4.总页数
		int totalPage = 0; 
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);

		//5. 显示到浏览器的数据.
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPage(cid,begin ,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	//查询某个商品详情
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	//根据二级分类的ID，查询商品
	public PageBean<Product> findByCsid(Integer csid ,Integer page) {
		int limit = 8; // 每页显示记录数.
		int totalPage = 0; // 总页数

		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		//查询总记录数
		Integer totalCount = productDao.findCountByCsid(csid);
		pageBean.setTotalCount(totalCount);

		// 总页数的封装
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);

		//数据的集合
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid, begin, limit);
		
		pageBean.setList(list);
		return pageBean;
	}

	//后台：查询所有商品（分页）
	public PageBean<Product> findByPage(Integer page) {
		//封装pagebean
		PageBean<Product> pageBean = new PageBean<Product>();
		Integer limit = 10;
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		
		//总记录数
		Integer totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		//总页数
		Integer totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		//每页显示数据的集合
		Integer begin = limit * (page -1 );
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//保存商品
	public void save(Product product) {
		productDao.save(product);
	}

	//删除商品
	public void delete(Product product) {
		productDao.delete(product);
	}

	//付款成功后 修改商品数量
	public void updateCount(Integer pid, Integer count) {
		productDao.updateCount(pid, count);
	}

}
