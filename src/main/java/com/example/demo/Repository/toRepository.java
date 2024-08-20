package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Customer.Customer;



public interface toRepository extends CrudRepository<Customer,Integer> {
	Customer findByEmail(String email);
	
	
}
