/**
 * 
 */
package com.gourab.myLearning.microservices.customerservice.util;

/**
 * @author GOURAB
 *
 */
public class InvalidCustomerException extends RuntimeException {

	public InvalidCustomerException(String message) {
		super(message);
	}
}
