package com.paymybuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.dto.UserDTO;
import com.paymybuddy.entity.Payment;
import com.paymybuddy.entity.User;
import com.paymybuddy.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody User login) {
		ResponseEntity<UserDTO> resp;
		System.out.println(login);
		UserDTO userDTO = userService.login(login.getEmail(), login.getPassword());
		if (userDTO != null) {
			resp = new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		} else {
			resp = new ResponseEntity<UserDTO>(HttpStatus.UNAUTHORIZED);
		}
		return resp;
	}
	
	@PostMapping("/create/account")
	public ResponseEntity<UserDTO> createAccount(@RequestBody User newUser) {
		ResponseEntity<UserDTO> resp;
		UserDTO userDTO = userService.createAccount(newUser);
		if (userDTO != null) {
			resp = new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		} else {
			resp = new ResponseEntity<UserDTO>(HttpStatus.UNAUTHORIZED);
		}
		return resp;
	}
	
	@GetMapping("/user/connections/{name}")
	public ResponseEntity<List<User>> userInfo(@PathVariable String name) {
		ResponseEntity<List<User>> resp;
		System.out.println(name);
		List<User> userConnections = userService.getUserConnections(name);
		if (userConnections != null) {
			resp = new ResponseEntity<List<User>>(userConnections, HttpStatus.OK);
			
		} else {
			resp = new ResponseEntity<List<User>>(HttpStatus.UNAUTHORIZED);
		}
		return resp;
	}
	
	@GetMapping("/user/payments/{name}")
	public ResponseEntity<List<Payment>> userPayments(@PathVariable String name) {
		ResponseEntity<List<Payment>> resp;
		System.out.println(name);
		List<Payment> userPayments = userService.getUserPayments(name);
		if (userPayments != null) {
			resp = new ResponseEntity<List<Payment>>(userPayments, HttpStatus.OK);
			
		} else {
			resp = new ResponseEntity<List<Payment>>(HttpStatus.UNAUTHORIZED);
		}
		return resp;
	}

}
