/**
 * 
 */
package com.gourab.myLearning.microservices.customerservice.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gourab.myLearning.microservices.customerservice.model.Customer;

/**
 * @author GOURAB
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);


}
