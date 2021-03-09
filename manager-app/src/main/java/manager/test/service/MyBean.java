package manager.test.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Configuration
public class MyBean {

    @Value("${name}")
    private String name;


    public MyBean(){
        log.info("MyBean name is {}",name);
    }

    public void print(){
        log.info("print MyBean name is {}",name);
    }

}
