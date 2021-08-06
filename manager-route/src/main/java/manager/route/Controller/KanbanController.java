package manager.route.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/kanbank")
public class KanbanController {

    @RequestMapping("/now")
    public LocalDateTime now(){
        return  LocalDateTime.now();
    }

    @RequestMapping("/request-info")
    public String requestInfo(HttpServletRequest request){
        return request.getRequestURI();

    }

}
