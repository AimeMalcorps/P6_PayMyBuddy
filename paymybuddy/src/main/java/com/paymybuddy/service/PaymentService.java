package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dto.PaymentDTO;
import com.paymybuddy.entity.BankAccount;
import com.paymybuddy.entity.Payment;
import com.paymybuddy.entity.User;
import com.paymybuddy.repository.BankAccountRepository;
import com.paymybuddy.repository.PaymentRepository;
import com.paymybuddy.repository.UserRepository;

@Service
public class PaymentService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BankAccountRepository bankAccountRepo;
	
	@Autowired
	private PaymentRepository paymentRepo;

	public String sendMoney(PaymentDTO paymentDTO) {

		try {
			User user = userRepo.findByName(paymentDTO.getUser().getName());
			
			BankAccount bankAccount = bankAccountRepo.findById(user.getId()).orElse(null);
			
			if (user != null && bankAccount != null && user.getConnections().stream().filter(connection -> connection.getId() == paymentDTO.getConnectionId())
					.findAny().isPresent() && paymentDTO.getAmount() <= bankAccount.getSold()) {
				Payment payment = new Payment();
				payment.setBankAccount(bankAccount);
				payment.setAmount(paymentDTO.getAmount());
				payment.setDescription(paymentDTO.getDescription());
				payment.setTarget(userRepo.findById(paymentDTO.getConnectionId()).orElse(null));
				paymentRepo.save(payment);
				return "Payment Sucess !";
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
