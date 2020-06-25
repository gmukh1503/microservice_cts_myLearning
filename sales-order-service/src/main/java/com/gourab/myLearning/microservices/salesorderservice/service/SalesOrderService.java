/**
 * 
 */
package com.gourab.myLearning.microservices.salesorderservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gourab.myLearning.microservices.salesorderservice.model.Customer;
import com.gourab.myLearning.microservices.salesorderservice.model.Item;
import com.gourab.myLearning.microservices.salesorderservice.model.OrderLineItem;
import com.gourab.myLearning.microservices.salesorderservice.model.OrderRequest;
import com.gourab.myLearning.microservices.salesorderservice.model.OrderResponse;
import com.gourab.myLearning.microservices.salesorderservice.model.SalesOrder;
import com.gourab.myLearning.microservices.salesorderservice.repository.CustomerRepository;
import com.gourab.myLearning.microservices.salesorderservice.repository.OrderLineItemRepository;
import com.gourab.myLearning.microservices.salesorderservice.repository.SalesOrderRepository;
import com.gourab.myLearning.microservices.salesorderservice.util.InvalidCustomerException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author GOURAB
 *
 */
@Service
public class SalesOrderService {

	public static final String  ITEM_SERVICE_URL = "http://item-service/service2/items/{itemName}";
	
	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private SalesOrderRepository salesOrderRepo;

	@Autowired
	private OrderLineItemRepository orederLineItemRepo;
	
	@Autowired
	private ItemServiceProxy itemServiceProxy;

	private List<String> portList;

	/**
	 * @param customer
	 */
	public void saveCustomer(Customer customer) {
		if (null != customer)
			customerRepo.saveAndFlush(customer);
		else
			throw new InvalidCustomerException("Customer could not be saved, object is null");
	}
	
	/**
	 * @param orderReq
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "createAnOrderFallback")
	public ResponseEntity<OrderResponse> createAnOrder(OrderRequest orderReq) {
		ResponseEntity<OrderResponse> respEntity = null;
		OrderResponse orderResp = null;
		List<OrderLineItem> orderLineItems = new ArrayList<>();
		portList = new ArrayList<>();
		Integer orderId = null;
		if (null != orderReq) {
			Integer customerId = orderReq.getCustomerId();
			List<Double> itemTotalList = new ArrayList<>();
			Double totalAmount = 0.0;
			Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
			if (optionalCustomer.isPresent()) {
				System.out.println("Order ::" + orderReq);
				Map<String, Integer> itemQuantityMap = orderReq.getItemQuantityList().stream()
						.collect(Collectors.toMap(t -> t.getName(), t -> t.getQuantity()));
				itemQuantityMap.forEach((name, quantity) -> {
					Item item = itemServiceProxy.getItemDetail(name);
					if (null != item) {
						if (portList.size() == 0) {
							portList.add(item.getPort());
						}
						System.out.println("item :-" + item);
						itemTotalList.add(quantity * item.getPrice());
						populateOrderLineItems(item, quantity, orderLineItems);

					}else {
						throw new RuntimeException();
					}
				});
				for (Double total : itemTotalList) {
					totalAmount = totalAmount + total;
				}
				orderId = populateAndSaveSalesOrder(orderReq, totalAmount);
				for (OrderLineItem orderLineItem : orderLineItems) {
					orderLineItem.setOrderId(orderId);
					orederLineItemRepo.saveAndFlush(orderLineItem);
				}

			} else {
				throw new InvalidCustomerException(
						"SalesOrderService.createAnOrder(): Customer Not Found in customer_sos table.");
			}
		}
		if (null != orderId) {
			orderResp = new OrderResponse();
			orderResp.setOrderId(orderId);
			orderResp.setMessage("Order Placed Successfully.");
		}else {
			throw new RuntimeException();
		}
		if(portList.size()>0) {
			orderResp.setItemServicePort(portList.get(0));
		}
		respEntity = new ResponseEntity<OrderResponse>(orderResp, HttpStatus.CREATED);
		return respEntity;
	}

	/**
	 * @param item
	 * @param quantity
	 * @param orderLineItems
	 */
	private void populateOrderLineItems(Item item, Integer quantity, List<OrderLineItem> orderLineItems) {
		OrderLineItem orderLineItem = new OrderLineItem();
		orderLineItem.setItemName(item.getName());
		orderLineItem.setItemQuantity(quantity);
		orderLineItems.add(orderLineItem);
	}

	/**
	 * @param orderReq
	 * @param totaOrderAmount
	 * @return
	 */
	private Integer populateAndSaveSalesOrder(OrderRequest orderReq, Double totaOrderAmount) {
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setCustomerId(orderReq.getCustomerId());
		salesOrder.setDescription(orderReq.getDescription());
		salesOrder.setOrderDate(orderReq.getOrderDate());
		salesOrder.setTotalPrice(totaOrderAmount);
		salesOrder = salesOrderRepo.saveAndFlush(salesOrder);
		return salesOrder.getId();
	}
	
	/**
	 * @param orderReq
	 * @return
	 */
	public ResponseEntity<OrderResponse> createAnOrderFallback(OrderRequest orderReq){
		OrderResponse orderResp = new OrderResponse();
		orderResp.setMessage("Order could not be placed");
		if(portList.size()>0) {
			orderResp.setItemServicePort(portList.get(0));
		}
		return new ResponseEntity<OrderResponse>(orderResp, HttpStatus.EXPECTATION_FAILED);
	}

	

}
