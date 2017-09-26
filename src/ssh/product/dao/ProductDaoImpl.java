package ssh.product.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ssh.product.domain.Product;
import ssh.web.base.BaseDaoImpl;

public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

	//查询热门商品，只显示10个
	public List<Product> findHot() {
		Session session = sessionFactory.getCurrentSession();

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Product.class);
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		criteria.add(Restrictions.eq("is_hot", 1));
		criteria.setFirstResult(0);
		criteria.setMaxResults(10);

		List<Product> list = criteria.list();
		return list;

	}

	//查询最新商品，只显示10个
	public List<Product> findNew() {
		Session session = sessionFactory.getCurrentSession();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Product.class);
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);

		criteria.addOrder(Order.desc("pdate"));
		criteria.setFirstResult(0);
		criteria.setMaxResults(10);

		List<Product> list = criteria.list();
		return list;
	}

	// 统计某个分类下的商品的总数:
	public Integer findCountByCid(Integer cid) {
		Session session = sessionFactory.getCurrentSession();
		//String hql = "select count(*) from Product p , CategorySecond cs where p.categorySecond = cs and cs.category.cid = ?";
		String hql = "select count(*) from Product p join p.categorySecond cs join cs.category c where c.cid = ?";

		Query query = session.createQuery(hql);
		query.setParameter(0, cid);

		List<Long> list = query.list();

		return list.get(0).intValue();
	}

	//显示商品集合
	public List<Product> findByPage(Integer cid, int begin, int limit) {
		Session session = sessionFactory.getCurrentSession();

		// String hql = "select p from Product p ,CategorySecond cs where p.categorySecond = cs and cs.category.cid = ?";
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, cid);
		query.setFirstResult(begin);
		query.setMaxResults(limit);

		List<Product> list = query.list();
		return list;
	}

	//根据ID查询商品
	public Product findByPid(Integer pid) {

		return sessionFactory.getCurrentSession().get(Product.class, pid);
	}

	//根据二级分类ID查询商品
	public Integer findCountByCsid(Integer csid) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(*) from Product p join p.categorySecond cs where cs.csid = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, csid);

		List<Long> list = query.list();
		return list.get(0).intValue();
	}

	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, csid);
		query.setFirstResult(begin);
		query.setMaxResults(limit); 

		List<Product> list = query.list();
		return list;
	}

	//后台：二级分类商品总记录数
	public Integer findCount() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(*) from Product" ;
		Query query = session.createQuery(hql);

		long obj = (long) query.uniqueResult();
		Integer count = (int) obj;
		System.out.println(count);
		return count;
	}

	//后台：二级分类商品每页显示记录
	public List<Product> findByPage(Integer begin, Integer limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Product" ;
		Query query = session.createQuery(hql);
		query.setFirstResult(begin);
		query.setMaxResults(limit);

		List<Product> list = query.list();

		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}



	//保存商品

	//根据ID删除商品

	//付款成功后 修改商品数量   UPDATE  product SET num = num - 1 WHERE pid = 35
	public void updateCount(Integer pid, Integer count) {
		Session session = sessionFactory.getCurrentSession();
		
		Product product = session.get(Product.class, pid);
		product.setNum(product.getNum() - count);
		session.update(product);
	}


}
