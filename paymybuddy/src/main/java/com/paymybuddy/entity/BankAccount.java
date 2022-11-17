package com.paymybuddy.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "bankAccount")
public class BankAccount {
	
	@Id
	private Integer id;
	
	@NotNull
	private float sold;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private User user;
	
//	@OneToMany
//	@JoinColumn(name = "payment_id")
//	private List<Payment> payments;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getSold() {
		return sold;
	}
	public void setSold(float sold) {
		this.sold = sold;
	}
	

}
