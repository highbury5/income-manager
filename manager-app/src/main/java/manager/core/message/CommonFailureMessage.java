package manager.core.message;

/**
 * 类名: SystemFailureMessage
 * 描述: 系统级别异常信息
 *
 * @author YangHongyu
 * @since 2019/5/18
 */
public enum CommonFailureMessage implements Message<Object> {

    /**
     * 请参考@PublicApi
     */
    SYSTEM_ERROR(PREFIX_SYSTEM + "201", "系统异常，请联系管理员！"),

    PATH_NOT_ACCESSIBLE(PREFIX_SYSTEM + "202", "对不起，该路径无法访问！"),

    BIZ_EXCEPTION(PREFIX_COMMON + "203", "业务异常！"),

    ILLEGAL_ARGUMENT(PREFIX_COMMON + "204", "非法参数!"),

    ARGUMENT_NOT_VALID(PREFIX_COMMON + "205", "参数不合法!"),

    MAX_UPLOAD_SIZE_EXCEEDED_EXCEPTION(PREFIX_COMMON + "206", "上传文件过大！"),

    BIND_ARGUMENT_FAILURE(PREFIX_COMMON + "207", "参数不匹配!"),

    PARAMETER_IS_TOO_LARGE(PREFIX_COMMON + "208", "参数值过大"),

    ADD_FAILURE(PREFIX_COMMON + "209", "新增记录失败！"),

    UPDATE_FAILURE(PREFIX_COMMON + "210","修改记录失败！"),

    DELETE_FAILURE(PREFIX_COMMON + "211","删除记录失败！"),

    READ_FAILURE(PREFIX_COMMON + "212","查询记录不存在！"),

    ARGUMENT_EMPTY(PREFIX_COMMON + "213", "参数不允许为空!"),

    OBJECT_EMPTY(PREFIX_COMMON + "214", "对象不允许为空!"),

    NOT_LOGIN(PREFIX_COMMON + "215", "请先登陆!"),

    TOKEN_INVALID(PREFIX_COMMON + "216", "token失效，请重新登录!"),

    ACCOUNT_INVALID(PREFIX_COMMON + "217", "用户已失效，请联系系统管理员!"),

    ROLE_NOT_EXIST(PREFIX_COMMON + "218","用户角色不存在！"),

    PRODUCT_NOT_EXIST(PREFIX_COMMON + "219","产品不存在！"),

    CHANNEL_NOT_EXIST(PREFIX_COMMON + "220","渠道不存在！"),

    TOTAL_NOT_MATCH(PREFIX_COMMON + "221","提成系数总和不为1"),

    UPLOAD_ERROR(PREFIX_COMMON + "222","文件上传失败"),

    DOWNLOAD_ERROR(PREFIX_COMMON + "223","文件下载失败"),

    INCOME_NOT_EXIST(PREFIX_COMMON + "224","进件不存在！"),

    ACCOUNT_NOT_EXIST(PREFIX_COMMON + "225", "用户不存在!"),

    INCOME_STATUS_ERROR(PREFIX_COMMON + "226","进件状态错误！"),

    INCOMECHANNEL_NOT_EXIST(PREFIX_COMMON + "227","渠道认领记录不存在！"),

    CHANNEL_NOT_MATCH(PREFIX_COMMON + "227","当前用户未属于任何渠道！"),

    ROLE_NOT_MATCH(PREFIX_COMMON + "228","当前用户不允许该操作！"),

    ;

    /**
     * 消息码
     */
    private final String resultCode;

    /**
     * 消息
     */
    private final String resultMessage;


    CommonFailureMessage(String resultCode, String resultMessage) {
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
