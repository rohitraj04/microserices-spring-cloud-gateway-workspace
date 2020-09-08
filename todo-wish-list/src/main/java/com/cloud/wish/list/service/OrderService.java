package com.cloud.wish.list.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cloud.wish.list.dto.Payment;
import com.cloud.wish.list.dto.TransactionRequest;
import com.cloud.wish.list.dto.TransactionResponse;
import com.cloud.wish.list.entity.Order;
import com.cloud.wish.list.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LoadBalancerClient loadBalancerClient;

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
		// without using gateway consuming service with service registry
		// Need to annotae RestTemplate with @LoadBalance to loadbancing if not done
		// error wiil br thrown
		// PAYMENT-SERVICE
//		Payment paymentResponse = restTemplate.postForObject("http://payment/api/payment/doPayment", payment,
//				Payment.class);

		ServiceInstance serviceInstance = loadBalancerClient.choose("PAYMENT-SERVICE1");
		//RibbonServer{serviceId='PAYMENT-SERVICE1', server=localhost:8001, secure=false, metadata={management.port=8001, jmx.port=51654}}
		String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/api/payment/doPayment";

		Payment paymentResponse = restTemplate.postForObject(url, payment, Payment.class);
		response = paymentResponse.getPaymentStatus().equals("success") ? "Payment Succsess" : "Payment Failed";

		orderRepository.save(order);

		// System.out.println(paymentResponse.getAmount());
		return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),
				response);
	}

	public TransactionResponse getToFall(TransactionRequest request) {
		Order order = request.getOrder();
		return new TransactionResponse(order, 0, "", "Circuit Breaker Enabled");
	}

	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}
}
