package ssh.order.service;

import java.util.List;

import ssh.order.domain.Order;
import ssh.user.domain.User;
import ssh.web.utils.PageBean;

public interface OrderService {

	//保存订单
	public Integer saveGetOid(Order order);
	
	//查询商品id
	public Order findByOid(Integer oid);

	//修改
	public void update(Order currOrder);
	
	//按用户查询订单
	public List<Order> findByUid(User existUser);

	//后台：按状态查询订单
	public PageBean<Order> findByPage(Integer state, Integer page);

	//后台：查询所有订单
	public PageBean<Order> findByPage(Integer page);

	public void deleteOrder(Order order);

}
