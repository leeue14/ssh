package ssh.user.domain;

import java.util.HashSet;
import java.util.Set;

import ssh.order.domain.Order;


/**
 * 用户模块的实体类对象
 *create table user(
	id int primary key auto_increment,
	username varchar(20) ,
	password varchar(20) ,
	name varchar(20),
	email varchar(30),
	phone varchar(20),
	addr varchar(50),
	sex varchar(10),
	state int,
	code varchar(64)
);
 */
public class User {
	private Integer uid;
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String addr;
	private String sex;
	private Integer state;
	private String code;
	
	//用户（1）	订单（多）
	private Set<Order> orders = new HashSet<Order>(); 
	
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public User() {
	}
	public User(Integer uid, String username, String password, String name,
			String email, String phone, String addr, String sex, Integer state,
			String code) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
		this.sex = sex;
		this.state = state;
		this.code = code;
	}

	
}
