package manager.advice;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import manager.core.constant.StringPool;
import manager.core.http.HttpHeaders;
import manager.core.message.Message;
import manager.core.message.Result;
import manager.core.message.Unpackaged;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 三段式返回
 *
 * @author yanghy
 * @since 2019-02-15
 */
@Slf4j
@ControllerAdvice({"manager"})
public class BizResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    public BizResponseBodyAdvice() {
        log.info("初始化BizResponseBodyAdvice...");
    }

    @Override
    public boolean supports(@NonNull MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        log.info("BizResponseBodyAdvice -> supports");
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        log.info("BizResponseBodyAdvice -> beforeBodyWrite");
        if (Unpackaged.class.isAssignableFrom(returnType.getParameterType())) {
            response.getHeaders().set(HttpHeaders.IS_PACKAGED_RESULT, StringPool.ZERO);
            return body;
        }
        response.getHeaders().set(HttpHeaders.IS_PACKAGED_RESULT, StringPool.ONE);
        if (Result.class.isAssignableFrom(returnType.getParameterType()) || body instanceof Result) {
            return body;
        }
        Result<Object> message = Result.toResult(Message.SUCCESS);
        message.setData(body);
        System.out.println("message : " + message.toJsonString());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();


        return message;
    }

}
