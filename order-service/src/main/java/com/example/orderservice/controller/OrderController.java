package com.example.orderservice.controller;


import com.example.basedomain.dto.Order;
import com.example.basedomain.dto.OrderEvent;
import com.example.orderservice.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	@Autowired
	private OrderProducer orderProducer;


	@PostMapping("/orders")
	public String placeOrder(@RequestBody Order order) {
		order.setOrderId(String.valueOf(UUID.randomUUID()));
		OrderEvent orderEvent = new OrderEvent();
		orderEvent.setStatus("PENDING");
		orderEvent.setOrder(order);
		orderEvent.setMessage("Order in pending");

		orderProducer.sendMessage(orderEvent);

		return "Successfully";
	}
}
