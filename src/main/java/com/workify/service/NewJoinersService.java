package com.workify.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.workify.entity.DAOUser;
import com.workify.input.UserIdClass;
import com.workify.repository.UserRepository;

@Service
public class NewJoinersService {

	@Autowired
	private UserRepository userData;

	@Autowired
	private LoginService loginService;

	public List<Map<String, Object>> getNewJoiners() {
		List<Map<String, Object>> newJoiners = new ArrayList<Map<String, Object>>();
		Set<String> recentDates = new HashSet<String>();

		/*
		 * Calendar date = Calendar.getInstance(); SimpleDateFormat formatter = new
		 * SimpleDateFormat("dd-MM-yyyy"); String currentDate =
		 * formatter.format(date.getTime()); String[] parts = currentDate.split("-");
		 * String currentDay = parts[0]; String currentMonth = parts[1]; String
		 * currentYear = parts[2]; int currentMonthInInt =
		 * Integer.parseInt(currentMonth);
		 * 
		 */

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		// get starting date
		cal.add(Calendar.DAY_OF_YEAR, -7);

		// loop adding one day in each iteration

		for (int i = 1; i <= 7; i++) {
			cal.add(Calendar.DAY_OF_YEAR, 1);
			recentDates.add(sdf.format(cal.getTime()));
		}

		for (DAOUser user : userData.findAll()) {
			Date userDoj = (Date) user.getDoj();
			SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
			String parsedDate = parser.format(userDoj);
			if (parsedDate != null && recentDates.contains(parsedDate)) {
				Map<String, Object> joiners = new HashMap<String, Object>();
				joiners.put("userId", user.getUserId());
				joiners.put("fullName", user.getFullName());
				joiners.put("empCode", user.getEmpCode());
				newJoiners.add(joiners);
			}
		}
		return newJoiners;
	}

	public String sendMailNewJoiners(UserIdClass newJoinersUserId) {
		Optional<DAOUser> fetchUser = userData.findById(newJoinersUserId.getUserId());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		DAOUser currUser = userData.findByUsername(name);
		if (fetchUser.isPresent() == true) {
			DAOUser user = fetchUser.get();
			String fullName = user.getFullName();
			String to = user.getOfficialMail();
			String from = "workify1410@gmail.com";
			String subject = "Welcome OnBoard";
			String message = "Hello " + fullName + " Welcome to Workify!!" + "\nFrom\n" + currUser.getFullName();
			if (LoginService.sendEmail(to, subject, message, from))
				return "Wish Sent Successfully";

		}

		return "Not able to Send Your Wish!!,Please try Again";
	}
}
