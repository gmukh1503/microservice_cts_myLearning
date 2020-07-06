package com.gourab.myLearning.microservices.salesorderservice.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gourab.myLearning.microservices.salesorderservice.config.FeignConfiguration;
import com.gourab.myLearning.microservices.salesorderservice.model.Item;

@FeignClient(name = "item-service")
@RibbonClient(name = "item-service")
public interface ItemServiceProxy {

	@GetMapping("/service2/items/{itemName}")
	public Item getItemDetail(@PathVariable String itemName);
	
}
