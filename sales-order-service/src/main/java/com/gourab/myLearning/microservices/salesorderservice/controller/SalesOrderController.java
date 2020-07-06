/**
 * 
 */
package com.gourab.myLearning.microservices.salesorderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gourab.myLearning.microservices.salesorderservice.model.OrderRequest;
import com.gourab.myLearning.microservices.salesorderservice.model.OrderResponse;
import com.gourab.myLearning.microservices.salesorderservice.service.SalesOrderService;

/**
 * @author GOURAB
 *
 */
@RestController
@RequestMapping("/service3")
public class SalesOrderController {

	@Autowired
	private SalesOrderService salesOrderService;
	
		@PostMapping("/orders")
		public ResponseEntity<OrderResponse> createOrder( @RequestBody OrderRequest order){
			return salesOrderService.createAnOrder(order);
		}
		
}
