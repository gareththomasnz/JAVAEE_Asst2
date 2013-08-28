package model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = -6837174276610847586L;
	private Long customerId;
	private String name;
	private List<Loan> loan;
	@OneToMany
	@JoinColumn (name = "customer_id") // define the foreign key customer_id in table loan
	public List<Loan> getLoan() {
		return loan;
	}
	public void setLoan(List<Loan> loan) {
		this.loan = loan;
	}

	@Id
	@GeneratedValue
	@Column(name="customer_id")
	public Long getCustomerId() {
		return customerId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setName(String name) {
		this.name = name;
	}


}