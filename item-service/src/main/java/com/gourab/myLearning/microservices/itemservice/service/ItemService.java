/**
 * 
 */
package com.gourab.myLearning.microservices.itemservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gourab.myLearning.microservices.itemservice.model.Item;
import com.gourab.myLearning.microservices.itemservice.model.Items;
import com.gourab.myLearning.microservices.itemservice.repository.ItemRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author GOURAB
 *
 */
@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepo;
	
	@Value("${server.port}")
	private String port;
	
	/**
	 * @return
	 */
	public ResponseEntity<Items> fetchAllItems() {
		ResponseEntity<Items> respEntity = null;
		List<Item> listOfItem = itemRepo.findAll();
		Items items = new Items();
		items.setItemList(listOfItem);
		if(null!=listOfItem && listOfItem.size()>0) {
			respEntity = new ResponseEntity<Items>(items, HttpStatus.FOUND);
		}			
		else {
			respEntity = new ResponseEntity<Items>(items, HttpStatus.NO_CONTENT);
		}
		return respEntity;
	}

	/**
	 * @param itemName
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getItemDetailFallback")
	public Item getItemDetail(String itemName) {
		Item item = null;
		Optional<Item> optionalItem = itemRepo.findByName(itemName);
		if(optionalItem.isPresent()) {
			item = optionalItem.get();
			item.setPort(port);
		}else {
			throw new RuntimeException();
		}
		return item;
	}
	
	/**
	 * @param itemName
	 * @return
	 */
	public Item getItemDetailFallback(String itemName, Throwable e) {
		System.out.println("--- Item-Service Fallback---->>>>:"+e.getMessage());
		Item item = new Item();
		item.setPort(port);
		return item;
	}


}
