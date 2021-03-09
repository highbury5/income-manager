package manager.core.message;

import lombok.Data;
import lombok.experimental.Accessors;
import manager.core.exception.BizException;

/**
 * 名称: Result
 * 描述: Json消息
 *
 * @author YangHongyu
 * @since 2018/1/9 22:40
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Message {

    private String resultCode;

    private String resultMessage;

    private T data;

    public static Result<Object> toResult(BizException e) {
        return new Result<>(e.getResultCode(), e.getResultMessage(), e.getData());
    }

    public static Result<Object> toResult(Message msg) {
        return new Result<>(msg.getResultCode(), msg.getResultMessage(), msg.getData());
    }

    public Result() {
        this(Message.SUCCESS);
    }

    public Result(String resultCode, String resultMessage, T data) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }

    public Result(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public Result(Message msg) {
        this.resultCode = msg.getResultCode();
        this.resultMessage = msg.getResultMessage();
    }

    public boolean isSuccess() {
        return Message.SUCCESS.getResultCode().equalsIgnoreCase(resultCode);
    }

}
