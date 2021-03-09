package manager.core.exception;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import manager.core.message.Message;
import manager.core.message.Result;

import java.lang.reflect.Type;

/**
 * 名称: Exception
 * 描述: 业务异常
 *
 * @author YangHongyu
 * @since 2017/6/18 13:30
 */
@Slf4j
public class BizException extends RuntimeException implements Message<Object> {

    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    private String resultCode;

    /**
     * 返回消息
     */
    private String resultMessage;

    /**
     * 附带信息
     * (异常不支持泛型，只能这样了)
     */
    private Object data;

    /**
     * 缺省的构造方法
     */
    public BizException() {
        super("");
    }

    /**
     * 构造方法
     *
     * @param resultMessage 异常消息
     */
    public BizException(String resultMessage) {
        this(Message.FAIL.getResultCode(), resultMessage);
    }

    /**
     * 构造方法
     *
     * @param resultCode 消息码
     * @param resultMessage 消息内容
     */
    public BizException(String resultCode, String resultMessage) {
        super(Message.toJsonString(resultCode, resultMessage, null));
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    /**
     * 构造方法
     *
     * @param resultCode 消息码
     * @param resultMessage 消息内容
     * @param data 附带信息
     */
    public BizException(String resultCode, String resultMessage, Object data) {
        super(Message.toJsonString(resultCode, resultMessage, data));
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }

    /**
     * 构造方法
     *
     * @param message 消息对象
     */
    public BizException(Message message) {
        super(message.toJsonString());
        this.resultCode = message.getResultCode();
        this.resultMessage = message.getResultMessage();
        this.data = message.getData();
    }

    /**
     * 构造方法
     *
     * @param message 消息对象
     * @param data 附带信息
     */
    public BizException(Message message, Object data) {
        super(Message.toJsonString(message.getResultCode(), message.getResultMessage(), data));
        this.resultCode = message.getResultCode();
        this.resultMessage = message.getResultMessage();
        this.data = data;
    }

    /**
     * 获取消息码
     *
     * @return 消息码
     */
    @Override
    public String getResultCode() {
        return resultCode;
    }

    /**
     * 设置消息码
     *
     * @param resultCode 消息码
     */
    public BizException setResultCode(String resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    /**
     * 获取消息内容
     *
     * @return 消息内容
     */
    @Override
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * 设置消息内容
     *
     * @param resultMessage 消息内容
     */
    public BizException setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
        return this;
    }

    /**
     * 获取附带信息
     * @return 附带信息
     */
    @Override
    public Object getData() {
        return data;
    }

    /**
     * 附带信息
     * @param data 附带信息
     */
    public BizException setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 转换为JSON字符串
     *
     * @return {"resultCode":"xxx","resultMessage":"xxx","data":...}
     */
    @Override
    public String toJsonString() {
        return Message.toJsonString(getResultCode(), getResultMessage(), getData());
    }

    /**
     * 转为异常消息
     * @return 异常消息
     */
    public Message toMessage() {
        return new Message() {
            @Override
            public String getResultCode() {
                return resultCode;
            }

            @Override
            public String getResultMessage() {
                return resultMessage;
            }

            @Override
            public Object getData() {
                return data;
            }
        };
    }

    /**
     * 转为异常消息
     * @return 异常消息
     */
    public <T> Result<T> toResult (Type dataType) {
        Result<T> result = new Result<>();
        result.setResultCode(getResultCode());
        result.setResultMessage(getResultMessage());
        if (getData() != null) {
            // 暂时这样处理吧，以后有更好解决方案时再改
            try {
                result.setData(JSON.parseObject(JSON.toJSONString(getData()), dataType));
            } catch (Exception e) {
                log.error("解析异常", e);
            }
        }
        return result;
    }

//    @Override
//    public Throwable fillInStackTrace() {
//        return this;
//    }

    public void logStackTraceMessage() {
        Throwable throwable = this;
        log.warn("业务异常:{}", throwable.getMessage(), throwable);
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        int index = 0;
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            if (index < 3) {
                log.warn("at " + stackTraceElement.toString());
                index++;
            } else {
                break;
            }
        }
    }

}
