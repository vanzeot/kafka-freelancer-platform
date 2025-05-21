package com.app.core.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskKafkaProducer {

    private final KafkaTemplate<String, TaskCreatedEvent> kafkaTemplate;

    private static final String TOPIC = "task-created";

    public void publish(TaskCreatedEvent event) {
        log.info("Publishing task created event to Kafka: {}", event);
        kafkaTemplate.send(TOPIC, event);
    }
}
