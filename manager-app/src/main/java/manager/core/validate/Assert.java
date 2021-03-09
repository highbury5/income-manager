package manager.core.validate;


import manager.core.message.CommonFailureMessage;
import manager.core.message.Message;
import org.springframework.util.StringUtils;

public abstract class Assert {


    public static void isBlank(String str) {
        if (StringUtils.isEmpty(str)) {
            throw CommonFailureMessage.ARGUMENT_EMPTY.toBizException();
        }
    }

    public static void isBlank(String str, Message messge) {
        if (StringUtils.isEmpty(str)) {
            throw messge.toBizException();
        }
    }

    public static void isNull(Object object) {
        if (object == null) {
            throw CommonFailureMessage.OBJECT_EMPTY.toBizException();
        }
    }

    public static void isNull(Object object,Message messge) {
        if (object == null) {
            throw messge.toBizException();
        }
    }
}