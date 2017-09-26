package ssh.order.service;

import java.util.List;

import ssh.order.domain.Order;
import ssh.order.domain.OrderItem;
import ssh.user.domain.User;
import ssh.web.utils.PageBean;

public interface OrderItemService {
	
	//根据oid 得到  订单
	public List<OrderItem> getOrderItemByOid(Integer oid);

}
