package manager.test.configuration;


import manager.test.condition.MacOSCondition;
import manager.test.condition.WinOsCondition;
import manager.test.service.ListService;
import manager.test.service.impl.MacListService;
import manager.test.service.impl.WinListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OSConfiguration {

    @Bean
    @Conditional(MacOSCondition.class)
    ListService macListService(){
        return new MacListService();
    }

    @Bean
    @Conditional(WinOsCondition.class)
    ListService winListService(){
        return  new WinListService();
    }

}
