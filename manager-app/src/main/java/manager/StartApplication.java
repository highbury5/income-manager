package manager;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableSwagger2Doc
@MapperScan("manager.mapper")
@EnableAsync
public class StartApplication {

    public static void main(String[] args){
        SpringApplication.run(StartApplication.class,args);
    }

}

