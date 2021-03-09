package manager.service.impl;

import lombok.extern.slf4j.Slf4j;
import manager.core.message.CommonFailureMessage;
import manager.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService{

    @Value("${com.manager.upload-path:/Users/huangzexiao/dev/income/files/}")
    String uploadPath;

    @Override
    public String uploadFile(@RequestParam MultipartFile file) throws IOException{
        if(file == null){
            throw CommonFailureMessage.UPLOAD_ERROR.toBizException();
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatDate = format.format(new Date());

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        log.info("old fileName is [{}]",fileName);
        String newFileName = formatDate + UUID.randomUUID().toString().replace("-","") + "." + suffix;
        File dest = new File(uploadPath + newFileName);
        log.info("new file is [{}]",dest);

        file.transferTo(dest);

        return newFileName;
    }

    public void downloadFile(String fileName, HttpServletResponse response){
        String file = uploadPath + fileName;
        try{
            Path path = Paths.get(uploadPath + fileName);
            OutputStream out = response.getOutputStream();
            Files.copy(path,out);
        }catch (IOException e){
            e.printStackTrace();
            throw CommonFailureMessage.DOWNLOAD_ERROR.toBizException();
        }
    }


}
