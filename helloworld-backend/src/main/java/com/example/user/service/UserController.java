package com.example.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.article.domain.PatientDTO;
import com.example.article.domain.TestUser;
import com.example.user.dao.AppRoleDAO;
import com.example.user.dao.AppUserDAO;
import com.example.user.model.AppUser;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	@Qualifier("patientServiceImpl")
	private PatientService patientService;
	
	@Autowired
	AppUserDAO appUserDAO;
	
	@Autowired
	AppRoleDAO appRoleDAO;
	
	@GetMapping("/{userName}")
	public @ResponseBody AppUser findUserAccount(@PathVariable("userName") String userName) {
		
		log.info("Start");
	    return appUserDAO.findUserAccount(userName);
	}	
	
	@GetMapping("/{userId}/roles")
	public @ResponseBody List<String> findUserRoles(@PathVariable("userId") Long userId) {
		
		log.info("Userid Roles Start");
	    return appRoleDAO.getRoleNames(userId);
	}
	
	////////////
	//
	// 샘    플
	//
	////////////
	//public ResponseEntity<TestUser> test() {
	@GetMapping("/test/sub-categories")
	public TestUser test() throws Exception {	
		log.info("여기는");
		
		// business logic
		List<PatientDTO> list = patientService.patientlist();
		System.out.println(":: list ::" + list);
		TestUser testUser = new TestUser();
		testUser.setName("조수현 테스트 입니다.");
		
	    return testUser;
	}	
	
}
