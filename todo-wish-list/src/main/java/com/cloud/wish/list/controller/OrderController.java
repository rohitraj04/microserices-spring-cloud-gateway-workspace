package com.cloud.wish.list.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.wish.list.dto.TransactionRequest;
import com.cloud.wish.list.dto.TransactionResponse;
import com.cloud.wish.list.entity.Order;
import com.cloud.wish.list.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/api/todo")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/orders")
	@HystrixCommand(fallbackMethod = "getTheCallOfDuty", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000") })
	public TransactionResponse bookOrder(@RequestBody TransactionRequest request) {
		return orderService.saveOrder(request);
	}

	public TransactionResponse getTheCallOfDuty(@RequestBody TransactionRequest request) {
		return orderService.getToFall(request);
	}

	@GetMapping("/getOrders")
	public List<Order> fetchAll() {
		return orderService.findAll();
	}

	@GetMapping("/hello")
	public String h1() {
		return "Dude";
	}
}
