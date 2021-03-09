package manager.core.sms;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.alibaba.fastjson.JSONObject;

import java.net.URLEncoder;

@Slf4j
public class SendMsgUtil {

        private static String SMS_URL = "http://api.1cloudsp.com/api/v2/single_send";

        //普通短信
        public static void sendsms(String templateId,String mobile) throws Exception {
            HttpClient httpClient = new HttpClient();
            PostMethod postMethod = new PostMethod(SMS_URL);
            postMethod.getParams().setContentCharset("UTF-8");
            postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());

            String accesskey = "MjRohDjP7hLwhO0z"; //用户开发key
            String accessSecret = "k7ixWgD1drCarwHcc06NNJ7Pm00O5xsQ"; //用户开发秘钥

            NameValuePair[] data = {
                    new NameValuePair("accesskey", accesskey),
                    new NameValuePair("secret", accessSecret),
                    new NameValuePair("sign", "【昱天成】"),
                    new NameValuePair("templateId", templateId),
                    new NameValuePair("mobile", mobile),
                    new NameValuePair("content", URLEncoder.encode("", "utf-8"))//（示例模板：{1}您好，您的订单于{2}已通过{3}发货，运单号{4}）
            };
            postMethod.setRequestBody(data);
            postMethod.setRequestHeader("Connection", "close");

            int statusCode = httpClient.executeMethod(postMethod);
            log.info("[SMS] statusCode : [{}] , body : [{}]");
            System.out.println("statusCode: " + statusCode + ", body: "
                    + postMethod.getResponseBodyAsString());
        }

        public static void main(String[] args) throws Exception {
            SendMsgUtil.sendsms("178299","13686817271");
            //普通短信
        }
}
