package ssh.web.base;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import ssh.adminuser.service.AdminUserService;
import ssh.category.service.CategoryService;
import ssh.categorysecond.service.CategorySecondService;
import ssh.order.service.OrderService;
import ssh.product.service.ProductService;
import ssh.user.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 基本Action对象，其它Action的父类
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>,RequestAware,
SessionAware, ApplicationAware {

	//1.封装数据 struts2 	模型驱动
	private T t;
	@Override
	public T getModel() {
		return this.t;
	}
	//实例化 T		反射
	public BaseAction() {
		try {
			//获得存储实例化类型参数的数组
			ParameterizedType param=(ParameterizedType) this.getClass().getGenericSuperclass();
			//获得实例化类型参数的值(类型)
			Class<T> clazz=(Class<T>) param.getActualTypeArguments()[0];
			//通过反射创建一个实例化类型参数的实例,并将它赋给t
			t=clazz.newInstance();
		} catch (Exception e) {
			new RuntimeException(e);
		}
	}

	//2.service注入
	@Resource(name="adminUserService")
	protected AdminUserService adminUserService;
	
	@Resource(name="categorySecondService")
	protected CategorySecondService categorySecondService;	

	@Resource(name="categoryService")
	protected CategoryService categoryService;

	@Resource(name="productService")
	protected ProductService productService;

	@Resource(name="orderService")
	protected OrderService orderService;	

	@Resource(name="userService")
	protected UserService userService;

	//4.共享到值栈
	public void push(Object obj){
		ActionContext.getContext().getValueStack().push(obj);
	}
	public void set(String key, Object value){
		ActionContext.getContext().getValueStack().set(key, value);
	}
	
	//5.共享到作用域	ioc
	public void put(String key,Object value){
		ActionContext.getContext().put(key, value);
	}
	public void putSession(String key, Object value){
		ActionContext.getContext().getSession().put(key, value);
	}
	public Object sessionGet(String value){
		return ActionContext.getContext().getSession().get(value);
	}

	//6.Map类型的request session application		ioc
	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}
