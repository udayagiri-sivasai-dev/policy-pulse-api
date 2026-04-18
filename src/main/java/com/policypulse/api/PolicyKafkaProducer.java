package com.policypulse.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PreDestroy;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class PolicyKafkaProducer {

    private final Producer<String, String> producer;
    private final String documentUploadedTopic;
    private final ObjectMapper objectMapper;

    public PolicyKafkaProducer(
            @Value("${kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${kafka.topic.document-uploaded}") String documentUploadedTopic,
            ObjectMapper objectMapper
    ) {
        this.documentUploadedTopic = documentUploadedTopic;
        this.objectMapper = objectMapper;

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.ACKS_CONFIG, "all");

        this.producer = new KafkaProducer<>(properties);
    }

    public void publishDocumentUploaded(PolicyDocumentUploadedEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);

            ProducerRecord<String, String> record = new ProducerRecord<>(
                    documentUploadedTopic,
                    String.valueOf(event.getPolicyId()),
                    message
            );

            producer.send(record);
            producer.flush();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert Kafka event to JSON", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to publish Kafka event", e);
        }
    }

    @PreDestroy
    public void closeProducer() {
        producer.close();
    }
}