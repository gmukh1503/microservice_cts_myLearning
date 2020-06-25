/**
 * 
 */
package com.gourab.myLearning.microservices.itemservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gourab.myLearning.microservices.itemservice.model.Item;
import com.gourab.myLearning.microservices.itemservice.model.Items;
import com.gourab.myLearning.microservices.itemservice.service.ItemService;

/**
 * @author GOURAB
 *
 */
@RestController
@RequestMapping("/service2")
public class ItemServiceController {
	
	@Autowired
	private ItemService itemService;

	@GetMapping("/items") 
	public ResponseEntity<Items> fetchAllItems(){
		return itemService.fetchAllItems();
	}
	
	@GetMapping("/items/{itemName}")
	public Item getItemDetail(@PathVariable String itemName){
		return itemService.getItemDetail(itemName);
	}
	
}
