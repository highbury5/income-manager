package manager.test.configuration;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ApplicationEnvRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args){
        log.info("application start success!");
    }

}
