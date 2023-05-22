package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.Token;

import io.restassured.response.Response;

public class GetImgurAPITest {

	Map<Object,Object> tokenMap;
	String accessToken;
	String accountUserName;
	
	
	@BeforeTest
	public void setAccessToken() {
		
		tokenMap = Token.getAccessToken();
		accessToken = tokenMap.get("access_token").toString();
		accountUserName = tokenMap.get("account_username").toString();
		
		System.out.println("Access Token: "+accessToken);
		System.out.println("Account Username: "+accountUserName);
		
	}
	
	@Test
	public void getAccountBlockStatus() {
		
		Map<String, String> authenticationToken = Token.getAuthenticationToken();
		Response response = RestClient.doGet(null, "https://api.imgur.com", "/account/v1/"+accountUserName+"/block", authenticationToken, null, true);
		
		System.out.println(response.prettyPrint());
		System.out.println(response.getStatusCode());
		
	}
	
	
	@Test
	public void uploadImagePostApiTest() {
		
		Map<String, String> clientIdMap = Token.getClientId();
		Map<String, String> formDataBodyMap = new HashMap<String,String>();
		formDataBodyMap.put("title", "Imgur upload file test title");
		formDataBodyMap.put("description", "Imgur upload file test description");
		
		//ContentType is Multipart for uploading file
		Response  response = RestClient.doPost("multipart", "https://api.imgur.com", "/3/upload", clientIdMap, null, true, formDataBodyMap);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
}
