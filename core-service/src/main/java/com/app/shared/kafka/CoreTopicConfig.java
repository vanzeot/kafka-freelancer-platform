package com.app.shared.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class CoreTopicConfig {

    @Bean
    public NewTopic taskTopic() {
        return TopicBuilder.name("core-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
