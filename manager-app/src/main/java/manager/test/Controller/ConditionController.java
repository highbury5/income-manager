package manager.test.Controller;


import io.swagger.annotations.Authorization;
import manager.annotation.IgnoreAuth;
import manager.test.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ConditionController {

    @Autowired
    ListService listService;

    @IgnoreAuth
    @GetMapping("/conditon-list")
    public void conditionList(){
        listService.list();
    }

}
