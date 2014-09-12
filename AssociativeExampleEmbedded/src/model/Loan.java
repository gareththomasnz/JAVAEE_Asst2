package model;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Loan")
public class Loan implements Serializable {

	private static final long serialVersionUID = -6837174276610847586L;
	private Long loanId;
	private Customer customer;
	private Book book;
	
	@Id
	@GeneratedValue
	@Column(name="loan_id")
	public Long getLoanId() {
		return loanId;
	}
	
	@ManyToOne (targetEntity=Customer.class)
	@JoinColumn(name="customer_id") // define customer_id as a foreign key in the loan table
	public Customer getCustomer() {
		return customer;
	}
	@ManyToOne (targetEntity=Book.class)
	@JoinColumn (name = "book_id") // define book_id as a foreign key in the loan table
	public Book getBook() {
		return book;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
}