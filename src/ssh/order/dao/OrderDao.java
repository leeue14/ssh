package ssh.order.dao;

import java.util.List;

import ssh.order.domain.Order;
import ssh.user.domain.User;
import ssh.web.base.BaseDao;

public interface OrderDao extends BaseDao<Order> {

	//保存订单
	public Integer saveGetOid(Order order);

	//查询商品id
	public Order findByOid(Integer oid);

	//修改订单
	
	//查询订单
	public List<Order> findByUid(User existUser);

	//查询订单数量
	public Integer findCount();

	//每页显示订单数据
	public List<Order> findByPage(Integer begin, Integer limit);

	//查询订单数量--状态
	public Integer findCount(Integer state);

	//每页显示订单数据--状态
	public List<Order> findByPage(Integer state, Integer begin, Integer limit);
}
