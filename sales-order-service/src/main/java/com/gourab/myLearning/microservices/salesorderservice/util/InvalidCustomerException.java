/**
 * 
 */
package com.gourab.myLearning.microservices.salesorderservice.util;

/**
 * @author GOURAB
 *
 */
public class InvalidCustomerException extends RuntimeException {

	public InvalidCustomerException(String message) {
		super(message);
	}
}
