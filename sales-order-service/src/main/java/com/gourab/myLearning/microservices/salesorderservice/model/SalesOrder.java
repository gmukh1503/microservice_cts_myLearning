/**
 * 
 */
package com.gourab.myLearning.microservices.salesorderservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author GOURAB
 *
 */
@Entity
@Table(name="sales_order")
@Getter
@Setter
public class SalesOrder implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="order_date")
	private Date orderDate;
	
	@Column(name="cust_id")
	private Integer customerId;
	
	@Column(name="order_desc")
	private String description;
	
	@Column(name="total_price")
	private Double totalPrice;

	@Override
	public String toString() {
		return "SalesOrder [id=" + id + ", orderDate=" + orderDate + ", customerId=" + customerId + ", description="
				+ description + ", totalPrice=" + totalPrice + "]";
	}
	
	
}
