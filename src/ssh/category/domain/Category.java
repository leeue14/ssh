package ssh.category.domain;

import java.util.HashSet;
import java.util.Set;

import ssh.categorysecond.domain.CategorySecond;

/**
 * 一级分类模块的实体类对象 create table category( cid int primary key auto_increment, cname
 * varchar(20) );
 */
public class Category {
	private Integer cid;
	private String cname;

	// 有二级分类的集合
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();

	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}

	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}

	

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
