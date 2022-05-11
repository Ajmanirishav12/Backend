package com.workify.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workify.entity.DAOUser;
import com.workify.input.UserIdClass;
import com.workify.repository.UserRepository;

@Service
public class AnniversaryService {

	@Autowired
	private UserRepository userData;

	public List<Map<String, Object>> getUserAnniversary() {

		List<Map<String, Object>> userAnniver = new ArrayList<Map<String, Object>>();
		Calendar date = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = formatter.format(date.getTime());
		String[] parts = currentDate.split("-");
		String currentDay = parts[0];
		String currentMonth = parts[1];
		String currentYear = parts[2];

		for (DAOUser user : userData.findAll()) {
			Date userDoj = (Date) user.getDoj();
			SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
			String parsedDate = parser.format(userDoj);
			String[] joinDate = parsedDate.split("-");
			if (parsedDate != null) {
				String joinDay = joinDate[0];
				String joinMonth = joinDate[1];
				String joinYear = joinDate[2];
				if (currentDay.equals(joinDay) && currentMonth.equals(joinMonth) && !(joinYear.equals(currentYear))) {
					Map<String, Object> anniver = new HashMap<String, Object>();
					anniver.put("userId", user.getUserId());
					anniver.put("fullName", user.getFullName());
					anniver.put("empCode", user.getEmpCode());
					anniver.put("noOfAnniversary",
							Integer.toString(Integer.valueOf(currentYear) - Integer.valueOf(joinYear)));
					userAnniver.add(anniver);
				}
			}
		}
		return userAnniver;
	}

	public String sendMailAnniversary(UserIdClass anniversaryId) {

		Optional<DAOUser> user = userData.findById(anniversaryId.getUserId());
		if (user.isPresent()) {
			DAOUser currUser = user.get();
			String to = currUser.getOfficialMail();
			String from = "workify1410@gmail.com";
			String message = "Dear " + currUser.getFullName() + ",\nCongratulations on your work anniversary!";
			String subject = "Anniversary Wishes";

			if (LoginService.sendEmail(to, subject, message, from)) {
				return "Mail Sent Successfully";
			}
		}
		return "Not able to Send Your Wish Please Try Again !!";
	}
}