/**
 * 
 */
package com.gourab.myLearning.microservices.salesorderservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author GOURAB
 *
 */
@Entity
@Table(name="customer_sos")
@Getter
@Setter
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cust_id")
	private Integer customerId;
	
	@Column(name = "cust_first_name")
	private String firstName;
	
	@Column(name = "cust_last_name")
	private String lastName;
	
	@Column(name = "cust_email")
	private String email;

	@Override
	public String toString() {
		return "CustomerSOS [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + "]";
	}
	
	
}
