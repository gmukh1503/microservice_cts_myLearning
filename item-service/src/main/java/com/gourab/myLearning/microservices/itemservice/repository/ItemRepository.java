/**
 * 
 */
package com.gourab.myLearning.microservices.itemservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gourab.myLearning.microservices.itemservice.model.Item;

/**
 * @author GOURAB
 *
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	Optional<Item> findByName(String itemName);

}
