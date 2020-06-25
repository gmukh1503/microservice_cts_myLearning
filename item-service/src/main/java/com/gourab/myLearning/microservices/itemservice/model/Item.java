/**
 * 
 */
package com.gourab.myLearning.microservices.itemservice.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * @author GOURAB
 *
 */
@Entity
@Table(name = "item")
@Getter
@Setter
public class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String description;
	private Double price;
	@Transient
	private String port;
	
	
	public Item() {	}
	
	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public Item(String name, String description, Double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", description=" + description + ", price=" + price + "]";
	}

	
	
	
	
}
