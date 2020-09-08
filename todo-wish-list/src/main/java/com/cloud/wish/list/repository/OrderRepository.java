package com.cloud.wish.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.wish.list.entity.Order;



@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
