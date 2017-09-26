package ssh.web.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource(name="sessionFactory")
	protected SessionFactory sessionFactory;  
	
	//T 编译时只是变量，在运行时才获得具体的类型
	private Class<?> beanClass;
	public BaseDaoImpl() {
		//获得运行时的类型，BaseDaoImpl<CrmStaff>称为被参数化的类型，将其从继承类中取过来赋给T
		//(ParameterizedType)this.getClass().getGenericSuperclass()被参数化的类型,为一个数组
		//在此处的类比为：获得ArrayList类，然后获得它的父类List，结果需要的是ArrayList类型
		//this指当前被new出来的那个类,.getClass指获得当前类,getGenericSuperclass指获得当前类的参数化类型数组
		ParameterizedType paramType=(ParameterizedType) this.getClass().getGenericSuperclass();
		//获得实际的参数，在这里参数只有一个paramType.getActualTypeArguments()[0]
		//在实际中可以有很多个,在继承类中传递多个参数时的写法<A,B,C,D,..>多个参数以","分隔传递
		beanClass=(Class<?>) paramType.getActualTypeArguments()[0];
	}

	//添加用户
	@Override
	public void save(T t) {
		sessionFactory.getCurrentSession().save(t);
	}

	//删除用户
	@Override
	public void delete(T t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	//修改用户
	@Override
	public void update(T t) {
		sessionFactory.getCurrentSession().update(t);
	}

	//添加或保存用户
	@Override
	public void saveOrUpdate(T t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	//无条件查询所有用户
	@Override
	public List<T> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from " + beanClass.getName());  
		return query.list();
	}

	//通过Id查询用户	String,Int等基本数据类型，都是java.io.Serializable的子类
	@Override
	public T findById(Serializable id) {
		return (T) sessionFactory.getCurrentSession().get(beanClass, id);
	}

}
