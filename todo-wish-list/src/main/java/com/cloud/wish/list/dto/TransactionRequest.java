package com.cloud.wish.list.dto;

import com.cloud.wish.list.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

	private Order order;
	private Payment payment;
}
