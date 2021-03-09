package manager.core.message;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import manager.core.exception.BizException;

import java.util.LinkedHashMap;

@Data
public class FileUploadResult<T> extends Result<T> {

    private String status;

    public  static final String SUCCESS = "done";

    public  static final String ERROR = "error";

    public FileUploadResult(String resultCode, String resultMessage, T data,String status) {
        super(resultCode,resultMessage,data);
        this.status = status;
    }

    public static FileUploadResult<Object> toResult(BizException e) {
        return new FileUploadResult<>(e.getResultCode(), e.getResultMessage(), e.getData(),ERROR);
    }

    public static FileUploadResult<Object> toResult(Message msg) {
        return new FileUploadResult<>(msg.getResultCode(), msg.getResultMessage(), msg.getData(),ERROR);
    }

    static String toJsonString(FileUploadResult fileUploadResult) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>(3);
        map.put("resultCode", fileUploadResult.getResultCode());
        map.put("resultMessage", fileUploadResult.getResultMessage());
        map.put("data", fileUploadResult.getData());
        map.put("status",fileUploadResult.getStatus());
        return JSON.toJSONString(map);
    }

}
