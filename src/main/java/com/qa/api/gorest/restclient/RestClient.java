package com.qa.api.gorest.restclient;

import java.io.File;
import java.util.Map;

import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class is having all HTTP methods which will call the api's and having generic methods for getting the 
 * response and fetch the values from response
 * @author mahen
 *
 */
public class RestClient {

	
	/**
	 * This method is used to GET call API
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param qParams
	 * @param log
	 * @return this method is returning response from GET call
	 */
	public static Response doGet(String contentType, String baseURI, String basePath, Map<String, String> token,
			Map<String, String> qParams, boolean log) {
		
		//set base uri
		if(setBaseURI(baseURI)) {
			//prepare request
			RequestSpecification request = createRequest(contentType, token, qParams, log);
			
			//Hit API and get response
			
			Response response = getResponse("GET", request, basePath);
			return response;
		}
		
			return null;		
		
	}
	
	
	/**
	 * This method is used to call POST API
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param qParams
	 * @param log
	 * @param obj
	 * @return returing response from POST call
	 */
	
	public static Response doPost(String contentType,String baseURI,String basePath,
			Map<String, String> token,Map<String, String> qParams,boolean log, Object obj) {
		
		
		//set base uri
				if(setBaseURI(baseURI)) {
					//prepare request
					RequestSpecification request = createRequest(contentType, token, qParams, log);
					
					//adding request payload
					addRequestPayload(request,obj);
					//Hit API and get response
					
					Response response = getResponse("POST", request, basePath);
					return response;
				}
				
					return null;
		
	}
	
//	public static Response doPost(String contentType,String baseURI,String basePath,
//			String token,Map<String, String> qParams,boolean log, String jsonFilePath) {
//		
//		
//		//set base uri
//				if(setBaseURI(baseURI)) {
//					//prepare request
//					RequestSpecification request = createRequest(contentType, token, qParams, log);
//					
//					//adding request payload
//					addRequestPayloadWithJsonFile(request,jsonFilePath);
//					//Hit API and get response
//					
//					Response response = getResponse("POST", request, basePath);
//					return response;
//				}
//				
//					return null;
//		
//	}
	
	private static void addRequestPayload(RequestSpecification request, Object obj) {
		
		if(obj instanceof String) {
			
			request.body(new File((String) obj));
			
		}else if(obj instanceof Map) {
			
			request.formParams( (Map<String, String>) obj);
			
		}else {
			
			String jsonPayload =TestUtil.getSerializedJSON(obj);
			request.body(jsonPayload);
		}
	}
	
//	private static void addRequestPayloadWithJsonFile(RequestSpecification request, String jsonFilePath) {
//		
//		request.body(new File(jsonFilePath));
//	}
	
	private static boolean setBaseURI(String baseURI) {
		
		if(baseURI==null || baseURI.isEmpty()) {
			System.out.println("Please pass correct base uri..it is either null or blank");
			return false;
		}
		
		try {
		RestAssured.baseURI = baseURI;
		return true;
		}catch (Exception e){
			
			System.out.println("Some Exception got occured while assigning the baseURI with RestAssured");
			return false;
		}
	}
	
	/*
	 * This method is to create request
	 */
	private static RequestSpecification createRequest(String contentType, Map<String, String> token, Map<String, String> qParams, boolean log) {
		
		RequestSpecification request;
		if(log) {
			
			request = RestAssured.given().log().all();
		}else {
			
			request = RestAssured.given();
		}
		
		if(token.size()>0) {
			
			//request.header("Authorization","Bearer "+token);
			request.headers(token);
			
		}
		
		if(!(qParams==null)) {
			
			request.queryParams(qParams);
		}
		
	if(contentType!=null) {
		if(contentType.equalsIgnoreCase("JSON")) {
			request.contentType(ContentType.JSON);
		}else if(contentType.equalsIgnoreCase("TEXT")) {
			request.contentType(ContentType.TEXT);
		}else if(contentType.equalsIgnoreCase("XML")) {
			request.contentType(ContentType.XML);
		}else if(contentType.equalsIgnoreCase("multipart")) {
			request.multiPart("image", new File("C:\\Users\\mahen\\Downloads\\img.jpeg"));
		}
		
	}
		return request;
	}
	
	private static Response getResponse(String httpMethod, RequestSpecification request,String basePath) {
		
		Response response =executeAPI(httpMethod, request, basePath);
		
		return response;
	}
	
	private static Response executeAPI(String httpMethod, RequestSpecification request,String basePath) {
		
		Response response = null;
		switch (httpMethod) {
		case "GET":
			response = request.get(basePath);
			break;
		case "POST":
			response =request.post(basePath);
			break;
		case "PUT":
			response =request.put(basePath);
			break;
		case "DELETE":
			response =request.delete(basePath);
			break;
		default:
			System.out.println("Please pass correct HTTP method");
			break;
		}
		
		return response;
	}
	
	
	
	
	
	
	
	
	
	
}
