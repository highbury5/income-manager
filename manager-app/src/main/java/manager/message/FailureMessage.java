package manager.message;

import manager.core.message.Message;

/**
 * 类名: FailureMessage
 * 描述: 系统级别异常信息
 *
 * @author YangHongyu
 * @since 2019/5/18
 */
public enum FailureMessage implements Message<Object> {

    /**
     * 枚举业务异常
     */
    ACCOUNT_NOT_EXIST("301", "用户不存在！"),

    ROLE_NOT_EXIST("302", "角色不存在！"),

    LOGIN_ERROR("303", "用户名或密码错误！"),

    MENU_NOT_EXIST("304","菜单不存在！")
    ;

    /**
     * 消息码
     */
    private final String resultCode;

    /**
     * 消息
     */
    private final String resultMessage;


    FailureMessage(String resultCode, String resultMessage) {
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
