package com.leaf.jpa.helloword;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@NamedQuery(name = "testNamedQuery", query = "FROM Customer c WHERE c.id = ?")
@Cacheable(true)
@Table(name = "JPA_CUTOMERS")
@Entity
public class Customer {

	private Integer id;
	private String lastName;

	private String email;
	private int age;

	private Date createdTime;
	private Date birth;

	private Set<Order> orders = new HashSet<>();

	// 映射单向 1-n 的关联关系
	// 使用 @OneToMany 来映射 1-n 的关联关系
	// 使用 @JoinColumn 来映射外键列的名称
	// 可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
	// 可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略.
	// 注意: 若在 1 的一端的 @OneToMany 中使用 mappedBy 属性, 则 @OneToMany 端就不能再使用 @JoinColumn
	// 属性了.
//	@JoinColumn(name = "CUSTOMER_ID")
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, mappedBy = "customer")
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	// @TableGenerator(name="ID_GENERATOR",
//	table="jpa_id_generators",
//	pkColumnName="PK_NAME",
//	pkColumnValue="CUSTOMER_ID",
//	valueColumnName="PK_VALUE",
//	allocationSize=100)
//	@GeneratedValue(strategy = GenerationType.TABLE,generator="ID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "LAST_NAME", length = 50, nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", lastName=" + lastName + ", email=" + email + ", age=" + age + ", createdTime="
				+ createdTime + ", birth=" + birth + "]";
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 工具方法. 不需要映射为数据表的一列.
	@Transient
	public String getInfo() {
		return "info";
	}

	public Customer(Integer id, String lastName, String email, int age, Date createdTime, Date birth,
			Set<Order> orders) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.createdTime = createdTime;
		this.birth = birth;
		this.orders = orders;
	}

	public Customer(String lastName, int age) {
		super();
		this.lastName = lastName;
		this.age = age;
	}

}
