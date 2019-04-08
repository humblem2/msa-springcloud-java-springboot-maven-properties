package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.config.ApplicationProperties;
import com.example.service.dto.TestUser;
import com.example.service.handler.RestTemplateErrorHandler;
import com.example.util.HttpUtil;
import com.example.util.UrlBuilder;
import com.example.web.TestController;

@Service
public class TestService {

	private final Logger log = LoggerFactory.getLogger(TestController.class);
	private final DiscoveryClient discoveryClient;
	private final ApplicationProperties applicationProperties;
	private final RestTemplate restTemplate;
	
    /**
     * 생성자 이용한 의존성 주입
     * @param ApplicationProperties applicationProperties
     * @param DiscoveryClient discoveryClient
     * @param RestTemplate restTemplate
     * @author Soohyun Cho
     */
	public TestService(DiscoveryClient discoveryClient, RestTemplate restTemplate, ApplicationProperties applicationProperties) {
		super();
		this.applicationProperties = applicationProperties;
		this.discoveryClient = discoveryClient;
		this.restTemplate = restTemplate;
		this.restTemplate.setErrorHandler(new RestTemplateErrorHandler()); // 템플릿 에러 핸들러
	}
	
	/**
     * Spring RestTemplate client sample
     * @param String authorization
     * @author Soohyun Cho
     */
	public TestUser test(String authorization) {
		log.info(":: test()"); 
		
		HttpEntity<TestUser> request = new HttpEntity(HttpUtil.getHeaders("authorization string 변수. 이건 사실 토큰 자리")); // 현재는 가짜 authorization
        
		log.info(":: request :: " + request);
		
        // GET /api/categories/{id}/sub-categories
        String url = (new UrlBuilder())
                .service(applicationProperties.getService().getBackend())	// e.g. apprication.yml에 등록된 backend. 컨피그서버에 등록시킬까?. Resource target service의 IP주소:Port번호
                .resource("/api/user/"+"test"+"/sub-categories")			// 요청 리소스 URI
                .build();
        
        log.info(":: url :: ", url);
        
        ResponseEntity<TestUser> responseEntity = restTemplate
                .exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<TestUser>() {});
        
        if(HttpStatus.OK != responseEntity.getStatusCode()) {
        	log.info(":: ERROR - HTTP status code = {} :: ", responseEntity.getStatusCode());
        }
        
        log.info(":: responseEntity.getBody() :: ", responseEntity.getBody());
        
		return responseEntity.getBody();
	}
	
}
