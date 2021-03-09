package manager.advice;

import lombok.extern.slf4j.Slf4j;
import manager.core.constant.StringPool;
import manager.core.message.Result;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

import static manager.core.http.HttpHeaders.IS_BIZ_EXCEPTION;
import static manager.core.http.HttpHeaders.IS_PACKAGED_RESULT;
import static manager.core.message.SystemFailureMessage.SYSTEM_EXCEPTION;

/**
 * SystemExceptionHandler 处理
 *
 * @author yanghy
 * @since 2019-05-09
 */
@Slf4j
public class SystemExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        pageNotFoundLogger.warn(ex.getMessage());

        Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
        if (!CollectionUtils.isEmpty(supportedMethods)) {
            headers.setAllow(supportedMethods);
        }
        Result<Object> result = Result.toResult(SYSTEM_EXCEPTION);
        result.setResultMessage(status.getReasonPhrase());
        return handleSystemExceptionInternal(ex, result, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            headers.setAccept(mediaTypes);
        }
        Result<Object> result = Result.toResult(SYSTEM_EXCEPTION);
        result.setResultMessage(status.getReasonPhrase());
        return handleSystemExceptionInternal(ex, result, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
            HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Result<Object> result = Result.toResult(SYSTEM_EXCEPTION);
        result.setResultMessage(status.getReasonPhrase());
        return handleSystemExceptionInternal(ex, result, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
            MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Result<Object> result = Result.toResult(SYSTEM_EXCEPTION);
        result.setResultMessage(status.getReasonPhrase());
        return handleSystemExceptionInternal(ex, result, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Result<Object> result = Result.toResult(SYSTEM_EXCEPTION);
        result.setResultMessage(status.getReasonPhrase());
        return handleSystemExceptionInternal(ex, result, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
            ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Result<Object> result = Result.toResult(SYSTEM_EXCEPTION);
        result.setResultMessage(status.getReasonPhrase());
        return handleSystemExceptionInternal(ex, result, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(
            ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Result<Object> result = Result.toResult(SYSTEM_EXCEPTION);
        result.setResultMessage(status.getReasonPhrase());
        return handleSystemExceptionInternal(ex, result, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Result<Object> result = Result.toResult(SYSTEM_EXCEPTION);
        result.setResultMessage(status.getReasonPhrase());
        return handleSystemExceptionInternal(ex, result, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Result<Object> result = Result.toResult(SYSTEM_EXCEPTION);
        result.setResultMessage(status.getReasonPhrase());
        return handleSystemExceptionInternal(ex, result, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Result<Object> result = Result.toResult(SYSTEM_EXCEPTION);
        result.setResultMessage(status.getReasonPhrase());
        return handleSystemExceptionInternal(ex, result, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Result<Object> result = Result.toResult(SYSTEM_EXCEPTION);
        result.setResultMessage(status.getReasonPhrase());
        return handleSystemExceptionInternal(ex, result, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Result<Object> result = Result.toResult(SYSTEM_EXCEPTION);
        result.setResultMessage(status.getReasonPhrase());
        return handleSystemExceptionInternal(ex, result, headers, status, request);
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
            AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

        if (webRequest instanceof ServletWebRequest) {
            ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
            HttpServletRequest request = servletWebRequest.getRequest();
            HttpServletResponse response = servletWebRequest.getResponse();
            if (response != null && response.isCommitted()) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Async timeout for " + request.getMethod() + " [" + request.getRequestURI() + "]");
                }
                return null;
            }
        }
        Result<Object> result = Result.toResult(SYSTEM_EXCEPTION);
        result.setResultMessage(status.getReasonPhrase());
        return handleSystemExceptionInternal(ex, result, headers, status, webRequest);
    }

    /**
     * Provides handling for RuntimeException.
     * @param ex the target exception
     * @param request the current request
     */
    @ExceptionHandler({
            RuntimeException.class,
            Exception.class
    })
    public ResponseEntity<Object> handleSystemException(Exception ex, WebRequest request) {
        Result<Object> result = Result.toResult(SYSTEM_EXCEPTION);
        return handleSystemExceptionInternal(ex, result, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<Object> handleSystemExceptionInternal(
            Exception ex, @Nullable Result message, HttpHeaders headers, HttpStatus status, WebRequest request) {
        headers.add(IS_BIZ_EXCEPTION, StringPool.ZERO);
        headers.add(IS_PACKAGED_RESULT, StringPool.ONE);
        log.error("系统异常:", ex);
        return handleExceptionInternal(ex, message, headers, status, request);
    }

}
