package com.bravo.bravobest.common.advice;

import com.bravo.bravobest.api.entity.ResultData;
import com.bravo.bravobest.common.exception.BravoBestException;
import com.bravo.bravobest.common.util.ResultUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class BravoBestControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(BravoBestControllerAdvice.class);

    private static final String DEFAULT_ERROR_MESSAGE = "数据操作有误，异常信息，请联系管理员！";
    private static final String DEFAULT_PARAM_CHECK_MESSAGE = "传入参数信息有误，请联系管理员！";


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BravoBestException.class})
    public ResultData handleBravoBestException(BravoBestException e){
        logger.error("自定义异常信息");
        logger.error(e.getMessage());
        String message = e.getMessage() == null ? DEFAULT_ERROR_MESSAGE : e.getMessage();
        return ResultUtils.fail(message);
    }

    /**
     * 拦截表单参数校验
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class})
    public ResultData handleRuntimeException(BindException e){
        logger.error("校验参数异常信息");
        logger.error(e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String field = fieldError.getField();
        String defaultMessage = fieldError.getDefaultMessage();
        System.out.println(field + "----" + defaultMessage);
        String message = StringUtils.isBlank(defaultMessage) ? DEFAULT_PARAM_CHECK_MESSAGE : defaultMessage;
        return ResultUtils.fail(message);
    }

    /**
     * 拦截普通参数校验
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ConstraintViolationException.class})
    public ResultData handleRuntimeException(ConstraintViolationException e){
        logger.error("校验参数异常信息");
        logger.error(e.getMessage());
        StringBuffer stringBuffer = new StringBuffer();
        String[] text = e.getMessage().split(",");
        for (String msg : text) {
            stringBuffer.append(msg.split(":")[1]);
        }
        String defaultMessage = stringBuffer.toString();
        System.out.println(defaultMessage);
        String message = StringUtils.isBlank(defaultMessage) ? DEFAULT_PARAM_CHECK_MESSAGE : defaultMessage;
        return ResultUtils.fail(message);
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Exception.class})
    public ResultData handleException(Exception e){
        logger.error("全局异常信息");
        logger.error(e.getMessage(),e);
        return ResultUtils.fail(DEFAULT_ERROR_MESSAGE);
    }


}
