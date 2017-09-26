package ssh.order.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ssh.order.domain.OrderItem;

public class OrderItemDaoImplTest {

	OrderItemDao orderItemDao;
	
	@Before  
    public void setUp() {  
        //这个要根据需要进行修改，在classes路径中去寻找配置文件。  
		ApplicationContext ctx =  
	            new ClassPathXmlApplicationContext("applicationContext.xml");  
        orderItemDao = (OrderItemDao) ctx.getBean("orderItemDao");  
          
    }  
	
	@Test
	public void test() {
		List<OrderItem> aItems = orderItemDao.getOrderItemByOid(42);
		
		System.out.println(aItems.size());
	}

}
