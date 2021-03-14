package manager.test.service.impl;

import lombok.extern.slf4j.Slf4j;
import manager.test.service.ListService;

@Slf4j
public class MacListService implements ListService {

    public void list(){
      log.info("mac list command : ls");
    }

}
