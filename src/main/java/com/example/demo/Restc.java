package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Restc {
	@RequestMapping("/test")
	public String getAllTo(){
		return "Just Checking";

}
}
