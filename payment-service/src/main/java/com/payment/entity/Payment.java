package com.payment.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAYMENT_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
	
	
	private static final long serialVersionUID = 3993142850836029265L;
	@Id
	@GeneratedValue
	private Long paymentId;
	private String paymentStatus;
	private String transactionId;
	//private int orderId;
	private Long orderId;
	private double amount;
	

}
