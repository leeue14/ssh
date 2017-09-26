package ssh.cart.action;

import javax.servlet.http.HttpServletRequest;
import ssh.cart.utils.Cart;

//购物模块的Action类
public interface CartAction  {

	//从session范围获得 购物车代码
	public Cart getCart();

	//添加到购物车的方法
	public String addCart();

	//清空购物车
	public String clearCart();


	// 移除购物项
	public String removeCart();

	//我的购物车:
	public String myCart();
}
