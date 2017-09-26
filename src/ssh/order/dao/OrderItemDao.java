package ssh.order.dao;

import java.util.List;

import ssh.order.domain.OrderItem;
import ssh.web.base.BaseDao;

public interface OrderItemDao extends BaseDao<OrderItem> {

	//根据oid 得到  订单
	public List<OrderItem> getOrderItemByOid(Integer oid);
}
