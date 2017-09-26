package ssh.categorysecond.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ssh.categorysecond.domain.CategorySecond;
import ssh.web.base.BaseDaoImpl;

public class CategorySecondDaoImpl extends BaseDaoImpl<CategorySecond> implements CategorySecondDao {

	//查询二级分页的记录数
	public Integer findCount() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(*) from CategorySecond";
		Query query = session.createQuery(hql);
		
		Object obj = query.uniqueResult();
		long ob = (long) obj;
		int count = (int) ob;
		System.out.println(count);
		return count;
	}

	public List<CategorySecond> findByPage(Integer begin, Integer limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from CategorySecond";
		Query query = session.createQuery(hql);
		query.setFirstResult(begin);
		query.setMaxResults(limit);
		
		List<CategorySecond> list = query.list();
		
		if (list.size() >0 ) {
			return list;
		}
		return null;
	}

	//二级分类保存

	//二级分类查询所有

}
