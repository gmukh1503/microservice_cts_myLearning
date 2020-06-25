/**
 * 
 */
package com.gourab.myLearning.microservices.salesorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gourab.myLearning.microservices.salesorderservice.model.Customer;

/**
 * @author GOURAB
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
