package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Customer.Customer;
import com.example.demo.Customer.Product;
import com.example.demo.Repository.toRepository;



@Service
@Transactional

public class toService {
	
	@Autowired
	toRepository torepository;
	
	
	
/*1 Getting All Customers Details*/
	public List<Customer> getAllTo(){
		List<Customer> n = new ArrayList<>();
		torepository.findAll().forEach(x -> n.add(x));
		return n;
	}
/*2 Creating Customer*/
	public void createTo (Customer c) {
		torepository.save(c);
	}
/*3 Getting Customer By ID*/
	public Customer getById(Integer id) {
		return torepository.findById(id).get();
	}
/*4 Updating Customer Details*/
	public void upadateTo(Integer id ,Customer c) {
		torepository.save(c);
	}
/*5 Deleting Customer Details*/
	public void deleteTo(Integer id) {
		torepository.deleteById(id);
	}
/*6 Creating Product inside the customer*/
	public String createProduct(Integer id , Product product) {
		
		String b  = "Sucessfull";
    	List<String> n =  new ArrayList<>();
		List<Customer> c = new ArrayList<>();
		
		String a  = product.getBookName();
		torepository.findById(id).get().getProducts().stream().forEach(x->{
			n.add(x.getBookName());
		});
		
		if(n.contains(product.getBookName())) {
			b  = "Have Already";
		}
		else {
			torepository.findById(id).get().getProducts().add(product);
			
		}
		return b;
		
	}
	
/*7 Getting All the Products irrespective of Customer*/
	public List<Product> findAllProducts() {
		List<Product> n =  new ArrayList<>();
		torepository.findAll().forEach(x-> n.addAll(x.getProducts()));
		return n;
	}
/*8 Getting All the Products respective of Customer Address*/
	public List<Product> findAllProductsInRange(String address){
		List<Product> n =  new ArrayList<>();
		List<Customer> p = new ArrayList<>();
		List<Customer> q = new ArrayList<>();
		torepository.findAll().forEach(x-> p.add(x));
		
	/*	p.stream().filter(x-> x.getAddress().equals(address)).forEach(x-> n.addAll(x.getProducts()));*/
		p.stream().forEach(x->{if(x.getAddress()!=null){q.add(x);}});
		for(int i=0 ; i<q.size() ; i++) {
			if(q.get(i).getAddress().equalsIgnoreCase(address)) {
				n.addAll(q.get(i).getProducts());
			}
		}
		
		return n;	
	}
	
	
	
/*9 Searching Specific Book in near address*/
	public List<Customer> findCustOfSameAddSameBook(String address , String bookName){
		List<Customer> r = new ArrayList<>();
		List<Customer> q = new ArrayList<>();
		List<Customer> p = new ArrayList<>();
		List<Customer> s = new ArrayList<>();
		
		torepository.findAll().forEach(x-> s.add(x));
		s.stream().forEach(x->{if(x.getAddress()!=null) {p.add(x);}});
		p.stream().filter(x-> x.getAddress().equalsIgnoreCase(address)).forEach(x->q.add(x));
		for(int i =0 ; i<q.size() ; i++) {
			for(int j=0 ; j<q.get(i).getProducts().size() ; j++)
			{ if(q.get(i).getProducts().get(j).getBookName().equalsIgnoreCase(bookName)) {
					r.add(q.get(i));
			}	
			}
	}
		return r;
	}


	
	
/*10 Authenticators using Email id and Password*/
    public Integer verify (Customer customer) {
    	
    	String a  ="Wrong emailId or Password";
    	Integer id  = 0;
    	
    	List b  = new ArrayList<>();
    	torepository.findAll().forEach(x -> b.add(x.getEmail()));
    	if(b.contains(customer.getEmail())) {
    	 a  = "Email Matched";
    	boolean c = torepository.findByEmail(customer.getEmail()).getPassword().equals(customer.getPassword());
    	 if(c) {
    		 id  = torepository.findByEmail(customer.getEmail()).getId();
    		 a  ="Jhakkas";
    	 }
    	 else {
    		 a  ="Email is good but password not";
    	 }
    	 
    	}
		return id;

}
    
/*11 Register New User*/  
    public String registerUser (Customer customer) {
    	String a = "Done" ;
        List b  = new ArrayList<>();
    	torepository.findAll().forEach(x -> b.add(x.getEmail()));
    	
    	if(b.contains(customer.getEmail())) {
    		a = "NotDone";
    	 }
    	else {
    		torepository.save(customer);
    	}
		return a;
		
    	
    }
    
// Deleting Books from Customer Profile 
    public String deleteBooks(Integer id , String name) {
    	
    	List<String> s  = new ArrayList<>();
    	String a  = "Not Found";
    	
    	torepository.findById(id).get().getProducts().stream().forEach(x->{
    		s.add(x.getBookName());
    	})  ;
    	
    	for(int i  =0 ; i<s.size() ;i++) {
    		
    		if(s.get(i).equalsIgnoreCase(name)) {
    			torepository.findById(id).get().getProducts().remove(i);
    			a = "Found and deleted";
    		}

    	}
    	
    	
		return a;
    	

    	
    }
    
    

   
	
}
    
    


