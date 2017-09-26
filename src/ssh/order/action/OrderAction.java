package ssh.order.action;

//订单
public interface OrderAction {

	// 保存订单执行的方法;
	public String saveOrder();

	//为订单付款的方法
	public String payOrder() throws Exception;

	//付款成功后的回调方法
	public String callBack();

	//按用户action查询订单
	public String findByUid();

	//查询订单
	public String findByOid();

	//后台：按状态查询订单
	public String adminFindByState();

	//后台：查询所有订单
	public String adminFindAll();

	//后台：订单修改状态
	public String adminUpdateState();

	//前台修改订单状态
	public String updateState();
	
	//后台：删除订单
	public String delete();
}
