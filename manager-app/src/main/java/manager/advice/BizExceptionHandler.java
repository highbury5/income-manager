package manager.advice;


import lombok.extern.slf4j.Slf4j;
import manager.core.constant.StringPool;
import manager.core.exception.BizException;
import manager.core.message.CommonFailureMessage;
import manager.core.message.FileUploadResult;
import manager.core.message.Result;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import static manager.core.http.HttpHeaders.IS_BIZ_EXCEPTION;
import static manager.core.http.HttpHeaders.IS_PACKAGED_RESULT;

/**
 * 业务异常统一处理
 *
 * @author yanghy
 * @since 2019-05-09
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BizExceptionHandler extends SystemExceptionHandler {

    /**
     * 业务异常返回状态码是不为200
     */
    private static final String BIZ_EXCEPTION_HTTP_STATUS_IS_200_FALSE = "0";

    /**
     * 业务异常返回状态码是为200
     */
    private static final String BIZ_EXCEPTION_HTTP_STATUS_IS_200_TRUE = "1";

    /**
     * 业务异常返回状态码是否为200（默认不为200）
     */
    private static final String BIZ_EXCEPTION_HTTP_STATUS_IS_200 =
            BIZ_EXCEPTION_HTTP_STATUS_IS_200_TRUE;

    public BizExceptionHandler() {
        log.info("初始化BizExceptionHandler...");
    }

    /**
     * Provides handling for biz exceptions.
     * @param ex the target exception
     * @param request the current request
     */
    @ExceptionHandler({
            BizException.class,
//            HystrixBadRequestException.class,
            IllegalArgumentException.class,
            MaxUploadSizeExceededException.class,
            UndeclaredThrowableException.class
    })
    public final ResponseEntity<Object> handleBizException(Exception ex, WebRequest request) throws Exception {
        Result<Object> message;
        if (ex instanceof BizException) {
            message = Result.toResult((BizException) ex);
        }
        else if (ex instanceof IllegalArgumentException) {
            message = Result.toResult(CommonFailureMessage.ILLEGAL_ARGUMENT);
            message.setData(ex.getMessage());
        }
        else if (ex instanceof MaxUploadSizeExceededException) {
            //message = Result.toResult(CommonFailureMessage.MAX_UPLOAD_SIZE_EXCEEDED_EXCEPTION);
            message = FileUploadResult.toResult(CommonFailureMessage.MAX_UPLOAD_SIZE_EXCEEDED_EXCEPTION);
            message.setData(ex.getMessage());
        }
        else if (ex instanceof UndeclaredThrowableException) {
            message = Result.toResult(CommonFailureMessage.BIZ_EXCEPTION);
            message.setData(ex.getMessage());
        }
        else {
            throw ex;
        }
        return handleBizExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    public ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
                                                      WebRequest request) {

        Result<Object> message = Result.toResult(CommonFailureMessage.BIND_ARGUMENT_FAILURE);
        List<ObjectError> objectErrors = ex.getAllErrors();
        if (objectErrors.size() > 0) {
            message.setResultMessage(objectErrors.get(0).getDefaultMessage());
        }
        return handleBizExceptionInternal(ex, message, headers, status, request);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        Result<Object> message = Result.toResult(CommonFailureMessage.ARGUMENT_NOT_VALID);
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (objectErrors.size() > 0) {
            message.setResultMessage(objectErrors.get(0).getDefaultMessage());
        }
        return handleBizExceptionInternal(ex, message, headers, status, request);
    }

    private ResponseEntity<Object> handleBizExceptionInternal(
            Exception ex, Result message, HttpHeaders headers, HttpStatus status, WebRequest request) {
        headers.add(IS_BIZ_EXCEPTION, StringPool.ONE);
        headers.add(IS_PACKAGED_RESULT, StringPool.ONE);

        if (ex instanceof BizException) {
            ((BizException) ex).logStackTraceMessage();
        } else {
            log.warn("业务异常:{}", ex.getMessage());
            logStackTraceMessage(ex);
        }

        // 业务异常返回200状态码开关
        if (BIZ_EXCEPTION_HTTP_STATUS_IS_200_TRUE.equalsIgnoreCase(BIZ_EXCEPTION_HTTP_STATUS_IS_200)) {
            status = HttpStatus.OK;
        }
        return handleExceptionInternal(ex, message, headers, status, request);
    }

    private void logStackTraceMessage(Throwable throwable) {
        Throwable e = throwable;
        while (e.getCause() != null) {
            e = e.getCause();
        }
        StackTraceElement[] stackTraceElements = e.getStackTrace();
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
