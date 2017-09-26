package ssh.order.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import ssh.order.dao.OrderDao;
import ssh.order.dao.OrderItemDao;
import ssh.order.domain.Order;
import ssh.order.domain.OrderItem;
import ssh.user.domain.User;
import ssh.web.utils.PageBean;

@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	//注入dao
	@Resource(name="orderItemDao")
	private OrderItemDao orderItemDao;

	@Override
	public List<OrderItem> getOrderItemByOid(Integer oid) {
	
		return orderItemDao.getOrderItemByOid(oid);
	}


}
