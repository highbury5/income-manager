package manager.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class TestUtil {

    private String status = "unready";

    public TestUtil(){
        log.info("TestUtil init.......");
        status = "ready";
    }

}
