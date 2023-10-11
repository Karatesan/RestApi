package com.karatesan.RESTAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karatesan.RESTAPI.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
