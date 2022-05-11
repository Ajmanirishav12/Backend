
package com.workify.controller;


import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.workify.service.LoginService;
import com.workify.entity.DAOUser;
import com.workify.entity.DAOUserPositionInfo;
import com.workify.input.UserDetailsInput;
import com.workify.input.UserIdClass;
import com.workify.output.UserDetailsOutput;
import com.workify.model.OrganizationTO;
import com.workify.service.OrganizationService;
import com.workify.service.CommonService;

@RestController
@RequestMapping("/workify/v1")
@CrossOrigin(origins = "*")
public class ResourceController {
	
	@Autowired
	private CommonService commonservice;
	
	
	
	@Autowired
	LoginService service;
	
	@Autowired
	OrganizationService orgService;

	@RequestMapping("/hellouser")
	public Map<String, Object> getUser(Authentication authentication)
	{
		Map<String, Object> res = commonservice.getLoggedinUser(authentication);
		return res;
	}
	
	@RequestMapping("/profile")
	public UserDetailsOutput getProfile(Authentication authentication)
	{
		UserDetailsOutput user = commonservice.getLoggedinUserProfile(authentication);
		return user;
	}
	
	
//	@RequestMapping("/empdetails")
//	public DAOUserPositionInfo getUserInfo(Authentication authentication) {
//		DAOUserPositionInfo user = commonservice.getLoggedinUserPositionInfo(authentication);
//		return user;
//	} 
	
	
	@RequestMapping("/helloadmin")
	public String getAdmin()     
	{
		return "Hello Admin";
	}
	
	
	@RequestMapping(value = "/orgdetails", method = RequestMethod.POST)
	public String saveUser(@RequestBody OrganizationTO organization) throws Exception {
		ResponseEntity.ok(orgService.saveOrgDetails(organization));
		return "Organization Details Saved!";
	}
	

	@RequestMapping("/allemployees")
	public List<UserDetailsOutput> getExistingEmployees(){
		return commonservice.getAllEmployees();
	}
	
	
	//to get the details of specific user by his username.
	@RequestMapping("/userdetails")
	public UserDetailsOutput getUserDetails(@RequestBody UserIdClass userIdClass) {
		Integer userId = userIdClass.getUserId();
		return commonservice.getUserDetails(userId);
	}
	
	//toEditUserDetails
	@RequestMapping("/edituser") 
	public String editUserDetails(@RequestBody UserDetailsInput userDetailsInput) {
		
		return commonservice.editUserDetails(userDetailsInput);
	}
}

