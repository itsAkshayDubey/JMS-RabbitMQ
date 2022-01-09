package com.akshay.rmqdemo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.akshay.rmqdemo.constants.Constants;
import com.akshay.rmqdemo.dto.OrderStatus;

@Component
public class Consumer {
	@RabbitListener(queues = Constants.QUEUE)
	public void consume(OrderStatus os) {
		System.out.println("Order received from queue. Order ID: "+os.getOrder().getOrderID()+" Item Name: "+os.getOrder().getName()+" Restaurant Name: "+os.getRestaurantName()+" Quantity: "+os.getOrder().getQty()+" Price: "+os.getOrder().getPrice()+" Rupees. "+" Total:"+(os.getOrder().getQty()*os.getOrder().getPrice())+" Rupees.");
	}
}
