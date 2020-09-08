package com.payment.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.payment.entity.Payment;
import com.payment.repository.PaymentRepository;

@Service
public class PaymentService {

	@Value("${server.port}")
	private String port;

	@Autowired
	private PaymentRepository paymentRepository;

	public Payment doPayment(Payment payment) {
		payment.setPaymentStatus(paymentProccessing());
		System.out.println(payment.getPaymentStatus());
		payment.setTransactionId(UUID.randomUUID().toString());
		try {
			Thread.sleep(3000);
			System.out.println("PORT Number is ****** +" + port);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// System.out.println(" **********Payment Id***** is :::"
		// +payment.getPaymentId());
		return paymentRepository.save(payment);

	}

	public String paymentProccessing() {
		return new Random().nextBoolean() ? "success" : "false";

	}

	public Payment findPaymentHistoryByOrder(Long orderId) {
		// TODO Auto-generated method stub
		return paymentRepository.findByOrderId(orderId);
	}
}
