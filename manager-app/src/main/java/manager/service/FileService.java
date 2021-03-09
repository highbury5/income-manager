package manager.service;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService {

    String uploadFile(@RequestParam MultipartFile file) throws IOException;

    void downloadFile(String fileName, HttpServletResponse response);
}
