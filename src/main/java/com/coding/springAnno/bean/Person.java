package com.coding.springAnno.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Person {

    public Person() {
    }

    @Value("张三")
    private String name;
    @Value("#{20-2}")
    private Integer age;

    @Value("${person.nickName}")
    private String nickName;
}
