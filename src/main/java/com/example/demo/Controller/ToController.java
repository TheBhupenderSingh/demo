package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Customer.Customer;
import com.example.demo.Customer.Product;
import com.example.demo.security.toService;



@CrossOrigin( origins = "https://book-issh-855e5b.netlify.app")
@RestController

public class ToController {
	
	@Autowired
	toService toservice ;
	
/*1 Getting All Customers Details*/
	@RequestMapping("/customer")
	public List<Customer> getAllTo(){
		return toservice.getAllTo();	
	}
/*3 Getting Customer By ID*/
	@RequestMapping("/customer/{id}")
	public Customer getTo(@PathVariable Integer id) {
		return toservice.getById(id);
	}
/*2 Creating Customer*/
	@RequestMapping(method=RequestMethod.POST ,value="/customer")
	public void createTo(@RequestBody Customer c) {
		toservice.createTo(c);
	}
/*4 Updating Customer Details*/
	@RequestMapping(method=RequestMethod.PUT ,value="/customer/{id}")
	public void updateTo(@PathVariable Integer id ,@RequestBody Customer c) {
		toservice.upadateTo(id, c);
	}
/*5 Deleting Customer Details*/
	@RequestMapping(method=RequestMethod.DELETE ,value="/customer/{id}")
	public void deleteTo(@PathVariable Integer id ) {
		toservice.deleteTo(id);
	}
	
/*6 Creating Product inside the customer*/
	@RequestMapping(method=RequestMethod.POST , value="/customer/{id}/product" )
	public ResponseEntity<String> createProduct(@RequestBody Product p , @PathVariable Integer id) {
		
		
		if(toservice.createProduct(id, p).equalsIgnoreCase("Sucessfull")){
			return ResponseEntity.ok("Done");
		}
		else {
			return ResponseEntity.ok("Already Have");
		}
		
		
	}
/*7 Getting All the Products irrespective of Customer*/
	@RequestMapping(method=RequestMethod.GET ,value="/product")
	public List<Product> findAllProducts(){
		return toservice.findAllProducts();
	}
	
	
/*8 Getting All the Products respective of Customer Address*/
	@RequestMapping(method=RequestMethod.GET ,value="/product/{address}")
	public List<Product> findAllProductsInRange(@PathVariable("address") String Address){
		return toservice.findAllProductsInRange(Address) ;
	}


/*9 Searching Specific Book in near address*/
	@RequestMapping(method=RequestMethod.GET ,value="/product/{address}/{bookName}")
    public List<Customer> findCustOfSameAddSameBook(@PathVariable("address") String address ,@PathVariable("bookName") String bookName){
		return toservice.findCustOfSameAddSameBook(address, bookName);
    }
/*10 Authenticators using Email id and Password*/
	@RequestMapping(method=RequestMethod.POST ,value="/login")
	public ResponseEntity<Integer> verify (@RequestBody Customer customer) {
		return ResponseEntity.ok(toservice.verify(customer)) ;
	}
	
	
/*11 Register New User*/ 	
	@RequestMapping(method=RequestMethod.POST , value="/SignUp" )
	public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
		if(toservice.registerUser(customer).equalsIgnoreCase("Done")){
			return ResponseEntity.ok("Success");	
		}
		else {
			return ResponseEntity.ok("Already have an account");
		}
	}
	
//Deleting Books for user
	@RequestMapping(method=RequestMethod.DELETE ,value="/customer/{id}/product/{name}")
	public String deleteTo(@PathVariable Integer id ,@PathVariable String name  ) {
		return toservice.deleteBooks(id, name);
	}
	
	
    
}
   

