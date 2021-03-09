package manager.controller.sys;

import lombok.extern.slf4j.Slf4j;
import manager.annotation.IgnoreAuth;
import manager.entity.Role;
import manager.util.TestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {

    @Autowired
    TestUtil testUtil;

    @IgnoreAuth
    @GetMapping("/demo")
    public String demo(){
        log.info("TestUtil status: {}",testUtil.getStatus());
        return "demo";


    }


}
