/**
 * 
 */
package com.gourab.myLearning.microservices.salesorderservice.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author GOURAB
 *
 */
@Getter
@Setter
public class OrderRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String description;
	private Date orderDate;
	private Integer customerId;
	private List<ItemQuantity> itemQuantityList;
	
	@Override
	public String toString() {
		return "Order [description=" + description + ", orderDate=" + orderDate + ", customerId=" + customerId
				+ ", itemQuantityList=" + itemQuantityList + "]";
	}
	
	
	
}
