package com.example.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.TestService;
import com.example.service.dto.TestUser;

/**
 * 이 클래스는 테스트 클래스이다
 * <pre>
 * <b>History:</b>
 *    	Soohyun Cho, 1.0, 07/16/18 --최초생성--
 * </pre>
 *
 * @author Soohyun Cho
 */
@RestController
//@RequestMapping("/api/testroot")
@RequestMapping("/root")
public class TestController {
	

	private final Logger log = LoggerFactory.getLogger(TestController.class);
	private final Environment env;
	//private final ApplicationProperties applicationProperties;
	private final TestService testService;
	
	/**
     * Dependency Injection taking Constructor
     * 
     * @param Environment env										현재 실행중인 애플리케이션에 등록된 환경정보(profile, properties) ?
     * @param ApplicationProperties applicationProperties			
     * @param TestService testService				
     * 
     * @author Soohyun Cho
     */
	public TestController(Environment env, TestService testService) {
		super();
		this.env = env;
		//this.applicationProperties =  applicationProperties;
		this.testService = testService;
	}
	
	//GET localhost:9010/testroot/test/파라미터
	@GetMapping("/test/{parameter}")
	public String test( @RequestHeader(value = HttpHeaders.AUTHORIZATION, required=false) String authorization 
						, @PathVariable("parameter") String parameter) {
		
		// debug
		System.out.println(":: authorization ::" 	+ authorization);
		System.out.println(":: parameter ::" 		+ parameter);
		
		// 로그인기능 있고 동시에 Spring Security 이용시
		//String login = SecurityUtils.getCurrentUserLogin(); // 유저아이디

		TestUser testUser = testService.test(authorization);
		
		//return "리턴응답개체";
        return testUser.getName();     
	}
	

	//GET localhost:9010/testroot/test/파라미터
	@GetMapping("/test")
	public String test_real( @RequestHeader(value = HttpHeaders.AUTHORIZATION, required=false) String authorization 
						) {
		
		// debug
		System.out.println(":: test_real authorization ::" 	+ authorization);
		//System.out.println(":: test_real parameter ::" 		+ parameter);
		
		// 로그인기능 있고 동시에 Spring Security 이용시
		//String login = SecurityUtils.getCurrentUserLogin(); // 유저아이디

		//TestUser testUser = testService.test(authorization);
		
		//return "리턴응답개체";
        return "나는 프론트엔드 서비스";     
	}
	
}
