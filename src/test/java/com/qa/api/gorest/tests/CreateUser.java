package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.Constants;
import com.qa.api.gorest.util.ExcelUtil;

import io.restassured.response.Response;

public class CreateUser {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public/v2/users";
	
	String token = "7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49";
	
	Map<String, String> tokenMap = new HashMap<String, String>();
	
	
	
	@DataProvider()
	public Object[][] getUserTestData() {
		
		Object [][] usrData= ExcelUtil.getTestData("Users");
		return usrData;
	}
	@Test(dataProvider ="getUserTestData",enabled=false)
	public void createUserAPIpostTest(String name,String email,String gender,String status) {
		
		tokenMap.put("Authorization","Bearer "+token);
		
			//POJO Class
		//User user = new User("SamhiK","samhik@abc.com","female","active");
		User user = new User(name,email,gender,status);
		
		
		
		Response response = RestClient.doPost("JSON", baseURI, basePath, tokenMap, null, true, user);
		
		System.out.println("Status Code is: "+response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	
	
	@Test
	public void createUserAPIwithJSONfileTest() {
		
		tokenMap.put("Authorization", "Bearer "+token);
		
		//Response response = RestClient.doPost("JSON", baseURI, basePath, token, null, true, Constants.CREATEUSER_JSONFILE_PATH);
		
		
		Response response = RestClient.doPost("JSON", baseURI, basePath, tokenMap, null, true, Constants.CREATEUSER_JSONFILE_PATH);
		
		System.out.println("Status Code is: "+response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	
	
	
	
//	@Test
//	public void createMultipleUserAPIwithJSONfileTest() {
//		
//		Response response = RestClient.doPost("JSON", baseURI, basePath, token, null, true, Constants.CREATEUSERS_JSONFILE_PATH);
//		
//		System.out.println("Status Code is: "+response.getStatusCode());
//		System.out.println(response.prettyPrint());
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
