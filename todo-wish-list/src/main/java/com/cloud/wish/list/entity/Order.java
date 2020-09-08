package com.cloud.wish.list.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDER_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

	
	private static final long serialVersionUID = 8298793793170871192L;
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private int quantity;
	private double price;
}
//(strategy = GenerationType.AUTO)