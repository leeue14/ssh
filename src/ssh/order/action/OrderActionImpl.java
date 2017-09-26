package ssh.order.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import ssh.cart.domian.CartItem;
import ssh.cart.utils.Cart;
import ssh.order.domain.Order;
import ssh.order.domain.OrderItem;
import ssh.order.service.OrderItemService;
import ssh.order.service.OrderService;
import ssh.product.service.ProductService;
import ssh.user.domain.User;
import ssh.web.utils.PageBean;
import ssh.web.utils.PaymentUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//订单
public class OrderActionImpl extends ActionSupport implements OrderAction {

	//注入orderService
	@Resource(name="orderService")
	private OrderService orderService;
	
	//注入orderService
	@Resource(name="orderItemService")
	private OrderItemService orderItemService;
	
	//注入orderService
	@Resource(name="productService")
	private ProductService productService;
	
	//////////////////////////////////////////////////////////////
	//表达式封装
	private Order order;
	private Integer oid;
	private String pd_FrpId;

	// 付款成功后的需要的参数:
	private String r3_Amt;
	private String r6_Order;

	//后台：订单查询所需的参数
	private Integer page;
	private Integer state;

	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	public void setState(Integer state) {
		this.state = state;
	}


	// 保存订单执行的方法;
	public String saveOrder(){
		order = new Order();
		/****************封装订单的数据*********/
		order.setOrdertime(new Date());
		order.setState(1); // 1 未付款   2 已经付款.  3.已经发货   4 已经收货.
		// 有些数据需要从购物车中获取:
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获得购物车:
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null){
			this.addActionMessage("您还没有购物!请先去购物!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// 订单所属的用户:
		User existUser = (User) request.getSession().getAttribute("existUser");
		if(existUser == null){
			this.addActionMessage("您还没有登录!请先去登录!");
			return "msg";
		}
		order.setUser(existUser);
		/********************封装订单项数据*************/
		// 订单项数据从 购物项的数据获得.
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		// 清空购物车.
		cart.clearCart();

		// 保存订单:
		Integer oid =  orderService.saveGetOid(order);

		order = orderService.findByOid(oid);

		return "saveOrderSuccess";
	}

	//为订单付款的方法
	public String payOrder() throws IOException{
		//修改订单
		//1.1查询ID订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());

		orderService.update(currOrder);

		// 付款:
		// 定义付款的参数:
		String p0_Cmd = "Buy";
		String p1_MerId = "10001126856";
		String p2_Order = order.getOid().toString();
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://127.0.0.1:8080/shop/order_callBack.action";
		String p9_SAF = "";
		String pa_MP = "";
		String pd_FrpId = "";
		String pr_NeedResponse = "1";
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, 
				p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,pd_FrpId , pr_NeedResponse, keyValue);

		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		System.out.println(sb.toString());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.sendRedirect(sb.toString());
		return NONE;
	}

	//付款成功后的回调方法
	public String callBack(){
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(2);// 修改订单状态.
		orderService.update(currOrder);

		//同时修改货存储量
		//1.首先SELECT COUNT,pid FROM orderitem WHERE oid = 42	得到pid  和 count
		List<OrderItem> oiLists = orderItemService.getOrderItemByOid(order.getOid());
		
		//2.修改存货量  UPDATE  product SET num = num - 1 WHERE pid = 35
		for (OrderItem orderItem : oiLists) {
			productService.updateCount(orderItem.getProduct().getPid(), orderItem.getCount());
		}
		
		
		this.addActionMessage("订单付款成功!订单号:"+r6_Order+" 付款金额:"+r3_Amt);
		return "msg";
	}

	//按用户action查询订单
	public String findByUid(){
		//获得用户对象
		User existUser = 
				(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");

		List<Order> oList = orderService.findByUid(existUser);

		ActionContext.getContext().getValueStack().set("oList", oList);
		return "findByUidSuccess";
	}

	//查询订单
	public String findByOid(){
		order =  orderService.findByOid(oid);
		return "findByOidSuccess";
	}

	//后台：按状态查询订单
	public String adminFindByState(){
		PageBean<Order> pageBean = orderService.findByPage(state,page);
		// 将PageBean的数据保存到页面:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindByStateSuccess";
	}

	//后台：查询所有订单
	public String adminFindAll() {
		PageBean<Order> pageBean = orderService.findByPage(page);
		// 将PageBean的数据保存到页面:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindAllSuccess";
	}

	//后台：订单修改状态
	public String adminUpdateState(){
		// 根据ID查询订单:
		order = orderService.findByOid(oid);
		order.setState(3);
		orderService.update(order);

		return "adminUpdateStateSuccess";
	}

	//前台修改订单状态
	public String updateState(){
		// 根据ID查询订单:
		order = orderService.findByOid(oid);
		order.setState(4);
		orderService.update(order);

		return "updateStateSuccess";
	}

	//后台:删除订单
	public String delete() {
		order = orderService.findByOid(oid);
		orderService.deleteOrder(order);
		return "deleteOrderSuccess";
	}

}
