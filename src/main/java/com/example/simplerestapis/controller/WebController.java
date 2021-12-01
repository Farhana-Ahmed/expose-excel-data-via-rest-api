package com.example.simplerestapis.controller;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.example.simplerestapis.models.Item;
import com.example.simplerestapis.models.SampleResponse;
import com.example.simplerestapis.service.GroceryService;

@RestController
public class WebController {
	
	
	@Autowired
	private GroceryService groceryService;

	@RequestMapping("/sample")
	public SampleResponse Sample(@RequestParam String name) {
		
		
		SampleResponse response = new SampleResponse();
		response.setId(1);
		response.setMessage("Your name is "+name);
		return response;

	}
	@RequestMapping("/test")
	public List<Item> genaratedResponse() throws OpenXML4JException, Exception{
		
		return groceryService.generateResults("");
	}
	
}