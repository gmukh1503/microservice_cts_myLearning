/**
 * 
 */
package com.gourab.myLearning.microservices.salesorderservice.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author GOURAB
 *
 */
@Getter
@Setter
public class ItemQuantity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Integer quantity;
	
	@Override
	public String toString() {
		return "ItemQuantity [name=" + name + ", quantity=" + quantity + "]";
	}
	
	
}
