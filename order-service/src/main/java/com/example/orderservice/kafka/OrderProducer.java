package com.example.orderservice.kafka;


import com.example.basedomain.dto.OrderEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data
@Slf4j
public class OrderProducer {
	private NewTopic topic;
	private KafkaTemplate<String, OrderEvent> kafkaTemplate;


	public void sendMessage(OrderEvent orderEvent){
		Message<OrderEvent> message = MessageBuilder
				.withPayload(orderEvent)
				.setHeader(KafkaHeaders.TOPIC,topic.name())
				.build();
		kafkaTemplate.send(message);
	}
}
