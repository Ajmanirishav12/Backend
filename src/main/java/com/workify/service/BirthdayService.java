package com.workify.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.workify.entity.DAOUser;
import com.workify.input.UserIdClass;
import com.workify.repository.UserRepository;

@Service
public class BirthdayService {
	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private LoginService loginService;

	public List<Map<String, Object>> loadUsersByDob() {
		List<Map<String, Object>> people = new ArrayList<Map<String, Object>>();
		// List<String> people = new ArrayList<String>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date currDate = new Date();
		String curr = formatter.format(currDate);
		String currDay = curr.substring(0, 2);
		String currMonth = curr.substring(3, 5);
		for (DAOUser user : userDao.findAll()) {
			Date bday = user.getDob();
			String currBday = formatter.format(bday);
			String day = currBday.substring(0, 2);
			String month = currBday.substring(3, 5);
			if (day.equals(currDay) && month.equals(currMonth)) {
				Map<String, Object> currUser = new HashMap<>();
				currUser.put("userId", user.getUserId());
				currUser.put("fullName", user.getFullName());
				currUser.put("empCode", user.getEmpCode());
				people.add(currUser);
			}
		}
		return people;
		// return res;
	}

	public String sendBdayWish(UserIdClass userId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currUserName = authentication.getName();
		Optional<DAOUser> bdayUser = userDao.findById(userId.getUserId());
		if (bdayUser.isPresent()) {
			DAOUser currBdayUser = bdayUser.get();
			DAOUser currLoggedInUser = userDao.findByUsername(currUserName);
			String to = currBdayUser.getOfficialMail();
			String from = "workify1410@gmail.com";
			String subject = "Birthday Wish.";
			String message = "Hey " + currBdayUser.getFullName() + ", A very happy birthday!" + "\n From "
					+ currLoggedInUser.getFullName();
			boolean canSend = LoginService.sendEmail(to,subject,message,from);
			if(canSend) return "Email sent successfully!";
			return "Not able to Send Your Wish!!,Please try Again";
		}
		return null;
	}

}
