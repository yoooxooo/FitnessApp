package app.it_academy.fitnessAppUsers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import java.time.Instant;
import java.util.Locale;

@Configuration
public class FormatterConfig {

    @Bean
    public Formatter<Instant> localDateTimeFormatter() {
        return new Formatter<>() {
            @Override
            public Instant parse(String text, Locale locale) {
                return Instant.ofEpochMilli(Long.parseLong(text));
            }

            @Override
            public String print(Instant object, Locale locale) {
                return object.toEpochMilli() + "";
            }
        };
    }
}
