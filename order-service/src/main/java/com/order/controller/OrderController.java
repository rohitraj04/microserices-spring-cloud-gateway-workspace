package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.order.dto.TransactionRequest;
import com.order.dto.TransactionResponse;
import com.order.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/orders")
	@HystrixCommand(fallbackMethod = "getTheCallOfDuty")
	public TransactionResponse bookOrder(@RequestBody TransactionRequest request) {
		return orderService.saveOrder(request);
	}
	
	@PostMapping("/placeOrders")
	@HystrixCommand(fallbackMethod = "getTheCallOfDuty", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" ,value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold" ,value = "10")
	})
	public TransactionResponse placeOrder(@RequestBody TransactionRequest request) {
		return orderService.saveOrder(request);
	}
	
	public TransactionResponse getTheCallOfDuty(@RequestBody TransactionRequest request) {
		return orderService.getToFall(request);
	}
}
