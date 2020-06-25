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
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String description;
	private Double price;
	private String port;

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", port=" + port + "]";
	}

}
