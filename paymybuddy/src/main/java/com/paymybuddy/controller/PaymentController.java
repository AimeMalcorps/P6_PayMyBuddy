package com.paymybuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.dto.PaymentDTO;
import com.paymybuddy.service.PaymentService;

@RestController
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/send/money")
	public ResponseEntity<String> sendMoney(@RequestBody PaymentDTO payment) {
		ResponseEntity<String> resp;
		String sendMessage = paymentService.sendMoney(payment);
		if (sendMessage != null ) {
			resp = new ResponseEntity<String>(sendMessage,HttpStatus.OK);
		} else {
			resp = new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
		return resp;
		
	}

}
