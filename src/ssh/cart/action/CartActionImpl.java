package ssh.cart.action;

import ssh.cart.domian.CartItem;
import ssh.cart.utils.Cart;
import ssh.product.domain.Product;
import ssh.web.base.BaseAction;

//购物模块的Action类

public class CartActionImpl extends BaseAction<Cart> implements CartAction  {

	//接受pid--ognl
	private Integer pid;
	//接受数量--ognl
	private Integer count;

	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	////////////////////////////////////////////////////////////////////////////
	
	//从session范围获得 购物车代码
	public Cart getCart() {
		// 从session的范围获得Cart对象.
		Cart cart = (Cart) sessionGet("cart");
		// 判断:
		if (cart == null) {
			// 创建购物车对象
			cart = new Cart();
			// 将购物车对象放入到session范围:
			putSession("cart", cart);
		}
		return cart;
	}

	//添加到购物车的方法
	public String addCart(){
		//查询商品的信息
		Product product = productService.findByPid(pid);

		//创建一个购物项
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(product);

		//获取购物车 需要依赖request对象
		Cart cart = getCart();
		cart.addCart(cartItem);
		return "addCartSuccess";
	}

	//清空购物车
	public String clearCart() {
		//获取cart对象
		Cart cart = getCart();
		cart.clearCart();

		return "clearCartSuccess";

	}


	// 移除购物项
	public String removeCart() {
		// 获取Cart对象.
		Cart cart = getCart();
		cart.removeCart(pid);
		return "removeCartSuccess";
	}

	//我的购物车:
	public String myCart(){
		return "myCartSuccess";
	}

}
