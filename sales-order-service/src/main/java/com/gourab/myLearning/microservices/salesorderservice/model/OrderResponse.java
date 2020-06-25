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
public class OrderResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private String message;
	private String itemServicePort;

}
