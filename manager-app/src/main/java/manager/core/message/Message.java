package manager.core.message;

import com.alibaba.fastjson.JSON;
import manager.core.exception.BizException;

import java.util.LinkedHashMap;

/**
 * 名称: Message
 * 描述: 消息（请使用枚举类实现）
 *
 * @author YangHongyu
 * @version 1.0
 * @since 2018/1/9 22:40
 */
public interface Message<T> {

    /**
     * 失败消息
     */
    Message FAIL = new Message<Object>() {
        @Override
        public String getResultCode() {
            return "401";
        }

        @Override
        public String getResultMessage() {
            return "sorry，系统出意外了！";
        }

        @Override
        public Object getData() {
            return null;
        }
    };

    /**
     * 成功消息
     */
    Message SUCCESS = new Message<Object>() {
        @Override
        public String getResultCode() {
            return "200";
        }

        @Override
        public String getResultMessage() {
            return "成功";
        }

        @Override
        public Object getData() {
            return null;
        }

    };

    /**
     * 系统异常 前缀
     */
    String PREFIX_SYSTEM = "";

    /**
     * 通用异常 前缀
     */
    String PREFIX_COMMON = "";

    /**
     * 获取消息码
     * @return 消息码
     */
    String getResultCode();

    /**
     * 获取消息内容
     * @return 消息内容
     */
    String getResultMessage();

    /**
     * 获取附带信息
     * @return 附带信息
     */
    T getData();

    /**
     * 转换为JSON字符串
     * @return {"resultCode":"xxx", "resultMessage":"xxx", "data":...}
     */
    default String toJsonString() {
        return toJsonString(getResultCode(), getResultMessage(), getData());
    }

    /**
     * 生成异常
     * @return 业务异常
     */
    default BizException toBizException() {
        return new BizException(getResultCode(), getResultMessage(), getData());
    }

    /**
     * 生成异常
     * @param data 附带信息
     * @return 业务异常
     */
    default BizException toBizException(Object data) {
        return new BizException(getResultCode(), getResultMessage(), data);
    }

    /**
     * 转换为JSON字符串
     * @param resultCode        消息码
     * @param resultMessage     消息内容
     * @param data              附带信息
     * @return {"resultCode":"xxx","resultMessage":"xxx","data":...}
     */
    static String toJsonString(String resultCode, String resultMessage, Object data) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>(3);
        map.put("resultCode", resultCode);
        map.put("resultMessage", resultMessage);
        map.put("data", data);
        return JSON.toJSONString(map);
    }

}
