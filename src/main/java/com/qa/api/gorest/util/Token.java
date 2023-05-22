package com.qa.api.gorest.util;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Token {

	public static Map<Object, Object> appTokenMap;
	public static Map<String, String> tokenMap = new HashMap<String, String>();
	public static String clietID ="37e9096b142a0e8";
	
	
	public static Map<Object,Object> getAccessToken() {
		
		Map<String, String> formParams = new HashMap<String, String>();
		formParams.put("refresh_token", "5d0be54ac8b00dd3ed59d0754a733092ef3e9660");
		formParams.put("client_id", "37e9096b142a0e8");
		formParams.put("client_secret", "e60617c38f07e048799f8245811e3c0912b6654a");
		formParams.put("grant_type", "refresh_token");
		
		
		RestAssured.baseURI = "https://api.imgur.com";
		
		//Non BDD approch
		
		RequestSpecification request = RestAssured.given().log().all();
		request.formParams(formParams);
		Response response = request.post("/oauth2/token");
		
		String accessToken = response.jsonPath().getString("access_token");
		
		System.out.println(response.prettyPrint());
		System.out.println("Access token : "+accessToken);
		
		appTokenMap = response.jsonPath().getMap("");//getMap("") - will print entire json object 
		
		return appTokenMap;
		
		//BDD approach
		
//	JsonPath tokenJson =	given().log().all()
//			.formParams(formParams)
//		.when().log().all()
//			.post("/oauth2/token")
//		.then().log().all()
//			.assertThat()
//				.statusCode(200)
//		.extract().jsonPath();
//	
//	System.out.println("Json Object: "+tokenJson.getMap("")); //getMap("") - will print entire json object 
//	
//	String accessToken = tokenJson.get("access_token");	
//	int accountId = tokenJson.get("account_id");
//	String accountUserName = tokenJson.get("account_username");
//	
//	System.out.println("Access Token :"+accessToken);
//	System.out.println("Access Id :"+accountId);
//	System.out.println("Access Username :"+accountUserName);
//			
//			 appTokenMap = tokenJson.getMap("");
//		
//		return appTokenMap;	
		
		
	}
	
	
	public static Map<String, String> getAuthenticationToken() {
		
		String authToken = appTokenMap.get("access_token").toString();
		System.out.println("Authentication Token: "+authToken);
		
		tokenMap.put("Authorization", "Bearer "+authToken);
		
		return tokenMap;
		
	}
	
	
	public static Map<String, String> getClientId() {
		
		System.out.println("Client ID is: "+clietID);
		tokenMap.put("Authorization", "Client-ID "+clietID);
		
		return tokenMap;
		
	}
	
	
	
	
	
}
