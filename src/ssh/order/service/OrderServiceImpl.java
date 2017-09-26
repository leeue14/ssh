package ssh.order.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import ssh.order.dao.OrderDao;
import ssh.order.domain.Order;
import ssh.user.domain.User;
import ssh.web.utils.PageBean;

@Transactional
public class OrderServiceImpl implements OrderService {

	//注入dao
	@Resource(name="orderDao")
	private OrderDao orderDao;

	//保存订单
	public Integer saveGetOid(Order order) {
		return orderDao.saveGetOid(order);		
	}

	//查询商品id
	public Order findByOid(Integer oid) {

		return orderDao.findByOid(oid);
	}

	//修改订单
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	//按用户查询订单
	public List<Order> findByUid(User existUser) {

		return orderDao.findByUid(existUser);
	}

	//后台：按状态查询订单
	public PageBean<Order> findByPage(Integer state, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//封装pageBea
		//当前页数
		pageBean.setPage(page);
		//每页显示记录数
		Integer limit = 10;
		pageBean.setLimit(limit);
		//设置总记录数
		Integer totalCount = orderDao.findCount(state);
		pageBean.setTotalCount(totalCount);

		//设置总页数
		Integer totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);

		//设置每页显示数据
		Integer begin = (page - 1) * limit; 
		List<Order> list = orderDao.findByPage(state,begin , limit);
		System.out.println("orderService"+list);
		pageBean.setList(list);

		return pageBean;
	}

	//后台：查询所有订单
	public PageBean<Order> findByPage(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//封装pageBea
		//当前页数
		pageBean.setPage(page);
		//每页显示记录数
		Integer limit = 10;
		pageBean.setLimit(limit);
		//设置总记录数
		Integer totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);

		//设置总页数
		Integer totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);

		//设置每页显示数据
		Integer begin = (page - 1) * limit; 
		List<Order> list = orderDao.findByPage(begin , limit);
		System.out.println("orderService"+list);
		pageBean.setList(list);

		return pageBean;
	}

	@Override
	public void deleteOrder(Order order) {
		orderDao.delete(order);
	}

}
