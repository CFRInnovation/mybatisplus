package com.coding.springAnno.config;

import com.coding.springAnno.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = {"classpath:person.properties"})
@Configuration
public class MyConfig {

    @Bean
    public Person person(){
        return new Person();
    }
}
