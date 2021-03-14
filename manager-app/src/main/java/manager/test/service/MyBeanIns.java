package manager.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBeanIns {

    @Autowired
    MyBean myBeanIn;

//    public MyBeanIns(){
//        myBeanIn.print();
//    }

}
