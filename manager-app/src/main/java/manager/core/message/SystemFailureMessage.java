package manager.core.message;

/**
 * 类名: SystemFailureMessage
 * 描述: 系统级别异常信息
 *
 * @author YangHongyu
 * @since 2019/5/18
 */
public enum SystemFailureMessage implements Message<Object> {

    /**
     * 服务器不可用
     */
    FALLBACK(PREFIX_SYSTEM + "0001", "系统繁忙！"),

    /**
     * 主键生成异常
     */
    ID_GENERATE_EXCEPTION(PREFIX_SYSTEM + "0003", "ID生成异常！"),

    SYSTEM_EXCEPTION(PREFIX_SYSTEM + "0004", "系统异常！"),

    RUNTIME_EXCEPTION(PREFIX_SYSTEM + "0005", "系统运行时异常！"),

    ;

    /**
     * 消息码
     */
    private final String resultCode;

    /**
     * 消息
     */
    private final String resultMessage;


    SystemFailureMessage(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

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
        // do nothing
        return null;
    }

}
