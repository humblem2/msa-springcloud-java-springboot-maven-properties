package com.example.util;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpUtil {

	public static HttpHeaders getHeaders(String authorization) {
		
        HttpHeaders headers = new HttpHeaders();
        if (authorization != null) {
            headers.setContentType(MediaType.APPLICATION_JSON); 	// MediaType 세팅
            headers.set("Authorization", authorization); 			// K,V 세팅
        }

        return headers;
	}
	
	public static Long getTotalCount(HttpHeaders headers) {
		
		Long totalCount = 0L;
        List<String> list =  headers.getValuesAsList("X-Total-Count");
        for(String v : list) {
        	try { totalCount = Long.parseLong(v); } catch(Exception e) {}
        	break;
        }

        return totalCount;
	}

	public static String getErpHeader(HttpHeaders headers, String key) {

        StringBuffer sb = new StringBuffer("");
        List<String> list =  headers.getValuesAsList(key);
        for(String v : list) {
        	sb.append(v);
        }
        return sb.toString();
	}
}
