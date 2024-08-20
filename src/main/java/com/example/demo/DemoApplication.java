package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Customer.Customer;
import com.example.demo.Customer.Product;
import com.example.demo.Repository.toRepository;



@SpringBootApplication

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner demo(toRepository repository) {
        return (args) -> {
            // Add data to the repository
        	Customer a  = new Customer();
        	List<Product> aA = new ArrayList<>();
        	Product aOne = new Product("Dune","Frank Herbert");
        	Product aTwo = new Product("Jaws","Peter Benchley");
        	Product aThree = new Product("Possession","A.S. Byatt");
        	Product aFour = new Product("Misery" ,"Stephen King");
        	a.setAddress("Punjab");
        	a.setId(1);
        	a.setName("Ranveer");
        	a.setNumber(789567878);
        	aA.add(aThree);
        	aA.add(aFour);
        	a.setProduct(aA);
        	a.setEmail("ranveer@gmail.com");
        	a.setPassword("Ran");
        	repository.save(a);
        	
        	Customer b  = new Customer();
        	b.setAddress("Delhi");
        	b.setId(2);
        	b.setName("Rajveer");
        	b.setNumber(736743990);
        	List<Product> bB = new ArrayList<>();
        	bB.add(aOne);
        	bB.add(aTwo);
        	b.setProduct(bB);
        	b.setEmail("rajveer@gmail.com");
        	b.setPassword("Raj");
        	repository.save(b);
        	

          
        };
        
    }
	
}



