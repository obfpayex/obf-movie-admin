package com.payex.vas.config;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@EnableRabbit
@Configuration
@Profile("!unit-test")
public class MqConfiguration {

//    private final ObjectMapper objectMapper; //To make sure that we use our own customized object-mapper
//    private final ApplicationProperties applicationProperties;
//
//    public MqConfiguration(ObjectMapper objectMapper, ApplicationProperties applicationProperties) {
//        this.objectMapper = objectMapper;
//        this.applicationProperties = applicationProperties;
//    }
//
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setMessageConverter(jsonMessageConverter());
//        factory.setConnectionFactory(connectionFactory);
//        factory.setConcurrentConsumers(applicationProperties.getRabbitmq().getConcurrentConsumers());
//        factory.setMaxConcurrentConsumers(applicationProperties.getRabbitmq().getMaxConcurrentConsumers());
//        return factory;
//    }
//
//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter(objectMapper);
//    }
}
