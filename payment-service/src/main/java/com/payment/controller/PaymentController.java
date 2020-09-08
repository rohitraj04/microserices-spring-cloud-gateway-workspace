package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.entity.Payment;
import com.payment.service.PaymentService;
	
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/doPayment")
	public Payment savePayment(@RequestBody Payment payment) {
		return paymentService.doPayment(payment);
	}
	
	@GetMapping("/{orderId}")
	public Payment findPaymentHistoryByOrder(@PathVariable Long orderId) {
		return paymentService.findPaymentHistoryByOrder(orderId);
	}
}
