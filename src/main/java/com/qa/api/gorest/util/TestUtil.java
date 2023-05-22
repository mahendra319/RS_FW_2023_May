package com.qa.api.gorest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

	
	/**
	 * This method is used to convert POJO object to JSON string
	 * @param obj
	 * @return json string
	 */
	public static String getSerializedJSON(Object obj) {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString=mapper.writeValueAsString(obj);
			System.out.println("JSON body Payload ====>"+jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonString;
	}
}
