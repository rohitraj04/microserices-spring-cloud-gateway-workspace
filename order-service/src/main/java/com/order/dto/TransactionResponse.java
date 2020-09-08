package com.order.dto;

import com.order.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

	private Order order;
	private double amount;
	private String transationId;
	private String msg;
}
