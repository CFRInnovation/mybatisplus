package com.coding.springTest;

import com.coding.springAnno.bean.Person;
import com.coding.springAnno.config.MyConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTestPropertyValue {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);

    @Test
    public void test01(){
       /* String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("Bean name: " + beanDefinitionName);
        }*/
        Person person = (Person)applicationContext.getBean("person");
        System.out.println(person);
    }
}
