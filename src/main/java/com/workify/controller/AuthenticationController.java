
package com.workify.controller;

import javax.servlet.http.HttpSession;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.workify.service.LoginService;
import com.workify.service.UserPositionInfoService;
import com.workify.config.JwtUtil;
import com.workify.input.ChangePassword;
import com.workify.input.CheckMail;
import com.workify.input.OTPClass;
import com.workify.input.UserDetailsInput;
import com.workify.model.AuthenticationRequest;
import com.workify.model.AuthenticationResponse;
import com.workify.model.UserPositionInfoTO;
import com.workify.model.UserTO;
import com.workify.repository.UserRepository;

@RestController
@RequestMapping("/workify/v1/login")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private LoginService userDetailsService;

	@Autowired
	private UserPositionInfoService userPositionInfoService;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtUtil.generateToken(userdetails);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public String saveUser(@RequestBody UserTO user) throws Exception {
//		boolean canSave = userDetailsService.save(user);
//		if(!canSave) return "username already exists!"; 
//		return "User Registered!";
//	} 

//	@RequestMapping(value = "/addempdetails", method = RequestMethod.POST)
//	public String saveUserEmployementDetails(@RequestBody UserPositionInfoTO userPositionInfoTO) {
//		ResponseEntity.ok(userPositionInfoService.saveUserPositionInfo(userPositionInfoTO));
//		return "Employement details of user saved!";
//	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveUser(@RequestBody UserDetailsInput user) throws Exception {
		boolean canSave = userDetailsService.save2(user);
		if (!canSave)
			return "username already exists!";
		return "User Registered!";
	}

	@ResponseBody
	@RequestMapping("/changepassword")
	public int  changePassword(@RequestBody ChangePassword changepass) {
	    String res = userDetailsService.changePassword(changepass);
	    if(res.equals("password changed!"))
		return 1;
	    
	    return 0;
	}

	
	@RequestMapping("/forgot")
	public int  checkEmail(@RequestBody CheckMail checkOffMail) {
        
		return userDetailsService.checkOffMail(checkOffMail);
		 
	}

	@RequestMapping("/verifyotp")
	public boolean fetchEnteredOtp(@RequestBody OTPClass enOtp) {

		boolean flag = userDetailsService.verifyOtp(enOtp);

		return flag;

	}

	@RequestMapping("/overridepassword")
	public String overridePassword(@RequestBody ChangePassword password) {
		return userDetailsService.overridePassword(password);
	}

}
