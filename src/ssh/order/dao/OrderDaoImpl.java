package ssh.order.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ssh.order.domain.Order;
import ssh.user.domain.User;
import ssh.web.base.BaseDaoImpl;

public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

	//保存订单
	public Integer saveGetOid(Order order) {
		return (Integer) sessionFactory.getCurrentSession().save(order);
		
	}

	//查询商品id
	public Order findByOid(Integer oid) {
		
		return sessionFactory.getCurrentSession().get(Order.class, oid);
	}
	
	
	//修改订单

	//查询订单
	public List<Order> findByUid(User existUser) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from Order o where o.user.uid=? order by ordertime desc";
		Query query = session.createQuery(hql);
		query.setParameter(0, existUser.getUid());
		
		List<Order> oList =  query.list();
		
		return oList;
		
	}

	//查询订单数量
	public Integer findCount() {
		String hql = "select count(*) from Order";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Long count = (Long) query.uniqueResult();
		
		return count.intValue();
	}

	//每页显示订单数据
	public List<Order> findByPage(Integer begin, Integer limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Order order by ordertime desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(begin);
		query.setMaxResults(limit);
		
		List<Order> list = query.list();
		System.out.println("ordreDao"+list);
		if (list != null && !list.isEmpty()) {
			return list;
		}			
		return null;		
	}

	//查询订单数量--状态
	public Integer findCount(Integer state) {
		String hql = "select count(*) from Order where state = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, state);
		Long count = (Long) query.uniqueResult();
		
		return count.intValue();
	}

	//每页显示订单数据--状态
	public List<Order> findByPage(Integer state, Integer begin, Integer limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Order  where state = ?  order by ordertime desc";
		Query query = session.createQuery(hql);
		query.setParameter(0, state);
		query.setFirstResult(begin);
		query.setMaxResults(limit);
		
		List<Order> list = query.list();
		
		if (list != null && !list.isEmpty()) {
			return list;
		}			
		return null;		
	}

}
