package com.order.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.order.dto.Payment;
import com.order.dto.TransactionRequest;
import com.order.dto.TransactionResponse;
import com.order.entity.Order;
import com.order.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestTemplate restTemplate;

	public TransactionResponse saveOrder(TransactionRequest request) {
		String response = "";
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		payment.setOrderId(order.getId());
		System.out.println(order.getPrice());
		payment.setAmount(order.getPrice());
		System.out.println(payment.getAmount());

//		Payment paymentResponse = restTemplate.postForObject("http://localhost:8001/api/payment/doPayment", payment,
//				Payment.class);
		//without using gateway consuming service with service registry
		//Need to annotae RestTemplate with @LoadBalance to loadbancing if not done error wiil br thrown
		//PAYMENT-SERVICE
		Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE1/api/payment/doPayment", payment,
				Payment.class);

		response = paymentResponse.getPaymentStatus().equals("success") ? "Payment Succsess" : "Payment Failed";

		orderRepository.save(order);

		System.out.println(paymentResponse.getAmount());
		return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),
				response);
	}
	
//	public List<TransactionResponse> getToFall(TransactionRequest request) {
//		Order order = request.getOrder();
//		return Arrays.asList(new TransactionResponse(order,0.0d," "," "));
//	}
	
	public TransactionResponse getToFall(TransactionRequest request) {
		Order order = request.getOrder();
		return new TransactionResponse(order, 0, "" , "Circuit Breaker Enabled");
	}
}
