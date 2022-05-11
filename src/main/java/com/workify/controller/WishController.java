package com.workify.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.workify.input.UserIdClass;
import com.workify.service.AnniversaryService;
import com.workify.service.BirthdayService;
import com.workify.service.NewJoinersService;

@RestController
@RequestMapping("/workify/v1/wish")
@CrossOrigin(origins = "*")
public class WishController {
	@Autowired
	private BirthdayService birthdayService;
	@Autowired
	private AnniversaryService anniversaryService;
	@Autowired
	private NewJoinersService  newJoinersService;

	
	@RequestMapping(value="/birthday")
	public List<Map<String, Object>> getBirthdays() {
		List<Map<String, Object>> bday = birthdayService.loadUsersByDob();
		return bday;
	}
	
    @RequestMapping(value="/anniversary")
	public List<Map<String, Object>> getAnniversary() {
    	
    	List<Map<String, Object>> joinAnniver = anniversaryService.getUserAnniversary();
		return joinAnniver;
	}
    @RequestMapping(value="/newjoiners")
	public List<Map<String, Object>> getNewJoiners() {
    	
    	List<Map<String, Object>> joinAnniver = newJoinersService.getNewJoiners();	
    		
		return joinAnniver;
	}
    
    @RequestMapping(value = "/mailanniversary")
    public String wishAnniversary(@RequestBody UserIdClass newJoinersId)
    {
    return anniversaryService.sendMailAnniversary(newJoinersId);

    }

    @RequestMapping(value = "/bdayemail")
    public String sendBdayEmail(@RequestBody UserIdClass userid) {
    	return birthdayService.sendBdayWish(userid);
    }

    @RequestMapping(value = "/mailnewjoiners")
    public String wishNewJoiners(@RequestBody UserIdClass newJoinersId)
    {
		return newJoinersService.sendMailNewJoiners(newJoinersId);
    	
    }
    

	
}
