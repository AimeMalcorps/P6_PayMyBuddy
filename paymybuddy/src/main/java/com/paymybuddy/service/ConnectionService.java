package com.paymybuddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.entity.User;
import com.paymybuddy.repository.UserRepository;

@Service
@Transactional
public class ConnectionService {

	@Autowired
	private UserRepository userRepo;

	public List<User> addConnections(Integer userId, String connectionName) {
		System.out.println(connectionName);
		User userToAdd = userRepo.findByName(connectionName);

		User user = userRepo.findById(userId).orElse(null);

		List<User> userConnections = user.getConnections();

		if (userToAdd != null) {
			System.out.println(userToAdd.getName());
			userConnections.add(userToAdd);
			userRepo.save(user);

		}
		return userConnections;
	}

}
