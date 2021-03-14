package manager.test.configuration;

import lombok.extern.slf4j.Slf4j;
import manager.util.TestUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TestConfiguration {

    public TestConfiguration(){
        log.info("cc init................................");
    }

    @Bean
    public TestUtil testUtil(){
        return new TestUtil();
    }

}
