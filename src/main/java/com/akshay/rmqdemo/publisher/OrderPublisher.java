package com.akshay.rmqdemo.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.akshay.rmqdemo.constants.Constants;
import com.akshay.rmqdemo.dto.Order;
import com.akshay.rmqdemo.dto.OrderStatus;

@RestController
@RequestMapping("/orders")
public class OrderPublisher {

	@Autowired
	RabbitTemplate rt;

	@PostMapping("/{restName}")
	public String bookOrder(@RequestBody Order order,@PathVariable String restName) {
		order.setOrderID(UUID.randomUUID().toString());
		OrderStatus os = new OrderStatus(order,Constants.PROCESSING_ORDER,Constants.ORDER_PLACED,restName);
		rt.convertAndSend(Constants.EXCHANGE,Constants.ROUTING_KEY,os);
		return "SUCCESS";
	}

}
