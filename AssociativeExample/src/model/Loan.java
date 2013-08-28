package model;


import java.io.Serializable;

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
	
	@ManyToOne
	@JoinColumn(name="customer_id") // define join column FK
	public Customer getCustomer() {
		return customer;
	}
	@ManyToOne
	@JoinColumn(name="book_id") // define join column FK
	public Book getBook() {
		return book;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	private Customer customer;
	private Book book;
	
	
	@Id
	@GeneratedValue
	@Column(name="loan_id")
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
}