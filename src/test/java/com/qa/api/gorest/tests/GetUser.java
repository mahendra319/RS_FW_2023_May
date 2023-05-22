package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class GetUser {
	
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public/v2/users";
	String token = "7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49";
	
	Map<String, String> tokenMap = new HashMap<String, String>();
	
	@Test
	public void getAllUserListAPI_Test() {
		
		tokenMap.put("Authorization","Bearer "+ token);
		Response response = RestClient.doGet("JSON", baseURI, basePath, tokenMap, null, true);
		
		System.out.println("Status Code is: "+response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	

	
	@Test
	public void getUserWithQueryParam() {
		
		Map<String, String> qParams = new HashMap<String, String>();
		qParams.put("name", "Verma");
		qParams.put("status", "active");
		
		tokenMap.put("Authorization","Bearer "+ token);
		Response response = RestClient.doGet("JSON", baseURI, basePath, tokenMap, qParams, true);
		
		System.out.println("Status Code is: "+response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	
	
	
}
