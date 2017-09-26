package ssh.order.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import ssh.order.domain.OrderItem;
import ssh.web.base.BaseDaoImpl;

public class OrderItemDaoImpl extends BaseDaoImpl<OrderItem> implements OrderItemDao {

	//根据oid 得到  订单	SELECT * FROM orderitem WHERE oid = 42
	public List<OrderItem> getOrderItemByOid(Integer oid) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from OrderItem where oid = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, oid);
		
		List<OrderItem> oList =  query.list();
		
		return oList;
	}

}
