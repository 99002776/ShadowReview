package com.boat.config;

import java.util.HashMap;
import java.util.Map;

 

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.boat.bean.Boat;
import com.boat.bean.BoatEvent;
import com.boat.bean.BoatLog;


@Configuration
public class BoatConfig {

     @Bean
        public ProducerFactory<String, Boat> producerFactory() {
            Map<String, Object> config = new HashMap<>();
            config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
           return new DefaultKafkaProducerFactory<>(config);
        }

     @Bean
     public ProducerFactory<String, BoatEvent> eventProducerFactory() {
         Map<String, Object> config = new HashMap<>();
         config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
         config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
         config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
     }
     
     @Bean
     public ProducerFactory<String, BoatLog> vehicleProducerFactory() {
         Map<String, Object> config = new HashMap<>();
         config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
         config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
         config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
     }
 


        @Bean
        public KafkaTemplate<String, Boat> kafkaTemplate() {
            
            return new KafkaTemplate<>(producerFactory());
            
        }


        @Bean
        public KafkaTemplate<String, BoatEvent> kafkaTemplateEvent() {
            
            return new KafkaTemplate<>(eventProducerFactory());
            
        }

        @Bean
        public KafkaTemplate<String, BoatLog> kafkaTemplateVehicle() {
            
            return new KafkaTemplate<>(vehicleProducerFactory());
            
        }
    
    
}
