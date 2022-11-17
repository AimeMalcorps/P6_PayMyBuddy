package com.paymybuddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.dto.UserDTO;
import com.paymybuddy.entity.Payment;
import com.paymybuddy.entity.User;
import com.paymybuddy.repository.PaymentRepository;
import com.paymybuddy.repository.UserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		
		user = userRepo.findByName(username);
		if (user != null) {
			return user;
		}
		return null;
	}
	
	public UserDTO login(String email, String password) {
		try {
			User user = userRepo.findByEmailAndPassword(email, password);
			if (user != null) {
				logger.info("User Login : " + user.getName());
				Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, null);
						SecurityContextHolder.getContext().setAuthentication(authentication);
				UserDTO userDTO = new UserDTO(user);
				return userDTO;
			}
		} catch(Exception e) {
			logger.info("User Unknown");
			return null;
		}
		logger.info("User Unknown");
		return null;
	}
	
	public UserDTO createAccount(User user) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		userRepo.save(user);
		return new UserDTO(user);
	}
	
	public List<User> getUserConnections(String name) {
		try {
			User user = userRepo.findByName(name);
			if (user != null) {
				logger.info("User Login : " + user.getName());
				List<User> connectionList = user.getConnections();
				return connectionList;
			}
		} catch(Exception e) {
			logger.info("User Unknown");
			return null;
		}
		logger.info("User Unknown");
		return null;
	}
	
	public List<Payment> getUserPayments(String name) {
		try {
			User user = userRepo.findByName(name);
			if (user != null) {
				logger.info("User Login : " + user.getName());
				List<Payment> userPayments = this.paymentRepo.findByBankAccountId(user.getId());
				return userPayments;
			}
		} catch(Exception e) {
			logger.info("User Unknown");
			return null;
		}
		logger.info("User Unknown");
		return null;
	}

	

}
