package com.workify.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mysql.cj.protocol.Message;
import com.workify.entity.DAOUser;
import com.workify.entity.DAOUserPositionInfo;
import com.workify.input.ChangePassword;
import com.workify.input.CheckMail;
import com.workify.input.OTPClass;
import com.workify.input.UserDetailsInput;
import com.workify.model.UserTO;
import com.workify.repository.UserPositionInfoRepository;
import com.workify.repository.UserRepository;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private UserRepository userDao;

	@Autowired
	private UserPositionInfoRepository daoUserPositionInfoRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	private static long savedOtp;
	private static String savedMail;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;

		DAOUser user = userDao.findByUsername(username);
		if (user != null) {
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
			return new User(user.getUsername(), user.getPassword(), roles);
		}

		throw new UsernameNotFoundException("User not found with the name " + username);
	}

//	public boolean save(UserTO user) {
//
//		DAOUser getUser = userDao.findByUsername(user.getUsername());
//		if (getUser != null)
//			return false;
//		
//		DAOUser newUser = new DAOUser();
//		newUser.setUserId(user.getUserId());
//		newUser.setUsername(user.getUsername());
//		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//		newUser.setActive(user.isActive());
//		newUser.setOfficialMail(user.getOfficialMail());
//		newUser.setMobile(user.getMobile());
//		newUser.setDob(user.getDob());
//		newUser.setDoj(user.getDoj());
//		newUser.setFirstName(user.getFirstName());
//		newUser.setLastName(user.getLastName());
//		newUser.setFullName(user.getFullName());
//		newUser.setMiddleName(user.getMiddleName());
//		newUser.setCountry(user.getCountry());
//		newUser.setState(user.getState());
//		newUser.setCity(user.getCity());
//		
//		newUser.setWorkLocation(user.getWorkLocation());
//		newUser.setRole(user.getRole());
//		newUser.setDepartment(user.getDepartment());
//		newUser.setAccountLocked(user.isAccountLocked());
//		newUser.setOrgId(user.getOrgId());
//		newUser.setEmpCode(user.getEmpCode());
//		newUser.setCreationDate(user.getCreationDate());
//		newUser.setModifiedDate(user.getModifiedDate());
//		newUser.setCreatedBy(user.getCreatedBy());
//		newUser.setModifiedBy(user.getModifiedBy());
//		newUser.setMarriageStatus(user.getMarriageStatus());
//		newUser.setDom(user.getDom());
//		newUser.setJobPosition(user.getJobPosition());
//		userDao.save(newUser);
//		return true;
//	}

	public boolean save2(UserDetailsInput user) {
		DAOUser getUser = userDao.findByUsername(user.getUserName());
		if (getUser != null)
			return false;
		DAOUser newUser = new DAOUser();
		DAOUserPositionInfo newUserPos = new DAOUserPositionInfo();
		if(user.getMobile().isEmpty()) user.setMobile("-1");
		if(user.getCity().isEmpty()) user.setCity("-1");
		if(user.getState().isEmpty()) user.setState("-1");
		if(user.getCountry().isEmpty()) user.setCountry("-1");
		if(user.getDesignation().isEmpty()) user.setDesignation("-1");
		if(user.getGrade().isEmpty()) user.setGrade("-1");
		if(user.getJobPosition().isEmpty()) user.setJobPosition("-1");
		if(user.getWorkLocation().isEmpty()) user.setWorkLocation("-1");
		if(user.getEmployementCategory().isEmpty()) user.setEmployementCategory("-1");
		if(user.getEmployementStatus().isEmpty()) user.setEmployementCategory("-1");
		if(user.getEmployementType().isEmpty()) user.setEmployementType("-1");
		newUser.setUserId(user.getUserId());
		newUser.setUsername(user.getUserName());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setActive(user.isActive());
		newUser.setOfficialMail(user.getOfficialMail());
		newUser.setMobile(user.getMobile());
		newUser.setDob(user.getDob());
		newUser.setDoj(user.getDoj());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setFullName(user.getFullName());
		newUser.setMiddleName(user.getMiddleName());
		newUser.setCountry(user.getCountry());
		newUser.setState(user.getState());
		newUser.setCity(user.getCity());
		newUser.setWorkLocation(user.getWorkLocation());
		newUser.setRole(user.getJobRole());
		newUser.setDepartment(user.getDepartment());
		newUser.setAccountLocked(user.isAccountLocked());
		newUser.setOrgId(user.getOrgId());
		newUser.setEmpCode(user.getEmpCode());
		newUser.setCreationDate(user.getCreatedDate());
		newUser.setModifiedDate(user.getModifiedDate());
		newUser.setCreatedBy(user.getCreatedBy());
		newUser.setModifiedBy(user.getModifiedBy());
		newUser.setMarriageStatus(user.getMarriageStatus());
		newUser.setDom(user.getDom());
		newUser.setJobPosition(user.getJobPosition());
		userDao.save(newUser);

		newUserPos.setUserId(newUser.getUserId());
		newUserPos.setEmpCode(user.getEmpCode());
		newUserPos.setDesignation(user.getDesignation());
		newUserPos.setGrade(user.getGrade());
		newUserPos.setEmpCategory(user.getEmployementCategory());
		newUserPos.setEmpStatus(user.getEmployementStatus());
		newUserPos.setEmpType(user.getEmployementType());
		newUserPos.setDepartment(user.getDepartment());
		newUserPos.setLocation(user.getWorkLocation());
		newUserPos.setL1ManagerId(user.getL1ManagerId());
		newUserPos.setL2ManagerId(user.getL2ManagerId());
		newUserPos.setHrManagerId(user.getHrManagerId());
		newUserPos.setOrgId(user.getOrgId());
		newUserPos.setIsActive(user.isActive());
		newUserPos.setCreationDate(user.getCreatedDate());
		newUserPos.setModifiedDate(user.getModifiedDate());
		newUserPos.setCreatedBy(user.getCreatedBy());
		newUserPos.setModifiedBy(user.getModifiedBy());
		daoUserPositionInfoRepository.save(newUserPos);
		// System.out.println(newUser.getUserId());
		// Integrating the email system :
		String to = newUser.getOfficialMail();
		String subject = "Welcome to workify!";
		String message = "Hello" + newUser.getFullName() + "\n"
				+ "Check the credentials to login and please change your password" + "\n" + "Username : "
				+ newUser.getUsername() + "\n" + "Password : " + user.getPassword();
		String from = "workify1410@gmail.com";
		if (sendEmail(to, subject, message, from)) {
			System.out.println("Email sent sucessfully!");
		} else {
			System.out.println("Invalid Email Id!");
		}
		return true;
	}

	public static boolean sendEmail(String to, String subject, String message, String from) {
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("workify1410@gmail.com", "Workify@b123");
			}
		});
		session.setDebug(true);
		MimeMessage credentials = new MimeMessage(session);
		try {
			credentials.setFrom(from);
			credentials.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
			credentials.setSubject(subject);
			credentials.setText(message);
			Transport.send(credentials);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String changePassword(ChangePassword changepass) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		DAOUser user = userDao.findByUsername(name);

		if (bcryptEncoder.matches(changepass.getOldPassword(), user.getPassword())) {
			user.setPassword(bcryptEncoder.encode(changepass.getNewPassword()));
			userDao.save(user);
			return "password changed!";
		}
		return "password doesnot match!";
	}

	public int  checkOffMail(CheckMail offMail) {
		
		  int netConnect = checkConnectivity();
		DAOUser user = userDao.findByofficialMail(offMail.getOffMail());
		if(netConnect==-1)
			return -1;
		else
		{
		if (user != null && netConnect==1) {
			LoginService.savedMail = user.getOfficialMail();
			sendOtp(user.getOfficialMail()); 
			return 1;
		}
		}
		return 0;
	}

	private int checkConnectivity() {
		// TODO Auto-generated method stub
		try {
	         URL url = new URL("http://www.google.com");
	         URLConnection connection = url.openConnection();
	         connection.connect();
             return 1;
		} catch (MalformedURLException e) {
	         return -1;
	      } catch (IOException e) {
	         return -1;
	      }
	}

	private void sendOtp(String officialMail) {
		Random random = new Random();
		long sendOtp = 100000 + random.nextInt(9000000);		
		LoginService.savedOtp = sendOtp;
		String from = "workify1410@gmail.com";
		String subject = "OTP From Workify";
		String msg = "OTP is\n" + sendOtp;
		LoginService.sendEmail(officialMail, subject, msg, from);

	}

	public boolean verifyOtp(OTPClass otp) {
		if (otp.getOtp() == LoginService.savedOtp) {
			return true;
		}
		return false;
	}
	

	public String overridePassword(ChangePassword password) {

		DAOUser user = userDao.findByofficialMail(LoginService.savedMail);

		user.setPassword(bcryptEncoder.encode(password.getNewPassword()));
		userDao.save(user);

		return "Password Changed Successfully";
	}

}
