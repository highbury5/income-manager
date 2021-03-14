package manager.test.service.impl;

import lombok.extern.slf4j.Slf4j;
import manager.test.service.ListService;

@Slf4j
public class WinListService implements ListService {

    public void list(){
        log.info("win list command : dir");
    }

}
