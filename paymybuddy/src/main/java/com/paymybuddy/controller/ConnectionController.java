package com.paymybuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.entity.User;
import com.paymybuddy.service.ConnectionService;

@RestController
public class ConnectionController {
	
	@Autowired
	private ConnectionService connectionService;
	
	@PostMapping("/connection/add/{userId}")
	public ResponseEntity<List<User>> getConnections(@PathVariable Integer userId, @RequestBody String connectionName) {
		
		ResponseEntity<List<User>> resp;
		
		List<User> connectionList = connectionService.addConnections(userId, connectionName);
		
		if (connectionList != null) {
			resp = new ResponseEntity<List<User>>(connectionList, HttpStatus.OK);
		} else {
			resp = new ResponseEntity<List<User>>(HttpStatus.UNAUTHORIZED);
		}
		return resp;
	}

}
