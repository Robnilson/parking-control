package com.api.parking_control.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig {

    // 'Z' indica que o horário está em UTC, deixando a hora com uma diferença de 3h no Brasil.
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    // Define um serializer personalizado que usa o formato de data e hora especificado na constante DATETIME_FORMAT
    public static LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        // Cria um módulo de suporte para classes de data e hora do Java 8
        JavaTimeModule module = new JavaTimeModule();
        // Adiciona o serializer personalizado ao módulo JavaTimeModule
        module.addSerializer(LOCAL_DATETIME_SERIALIZER);
        // Retorna um ObjectMapper configurado com o JavaTimeModule, para serializar corretamente LocalDateTime
        return new ObjectMapper().registerModule(module);
    }
}




