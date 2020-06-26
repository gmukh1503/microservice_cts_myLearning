/**
 * 
 */
package com.gourab.myLearning.microservices.customerservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


/**
 * @author GOURAB
 *
 */

@Entity
@Table(name="customer")
@Getter
@Setter
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	@JsonProperty("customerId") 
	private Integer customerId;
	
	@JsonProperty("email")
	private String email;
	
	@Column(name = "first_name")
	@JsonProperty("firstName")
	private String firstName;
	
	@Column(name = "last_name")
	@JsonProperty("lastName")
	private String lastName;

	@Override
	public String toString() {
		return "Customer [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
