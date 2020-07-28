/**
 * 
 */
package com.gourab.myLearning.microservices.salesorderservice.model;

import java.io.Serializable;

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
@Table(name="order_line_item")
@Getter
@Setter
public class OrderLineItem implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name="item_name")
	private String itemName;
	
	@Column(name="item_quantity")
	private Integer itemQuantity;
	
	@Column(name="order_id")
	private Integer orderId;

	@Override
	public String toString() {
		return "OrderLineItem [id=" + id + ", itemName=" + itemName + ", itemQuantity=" + itemQuantity + ", orderId="
				+ orderId + "]";
	}
	
	
}
