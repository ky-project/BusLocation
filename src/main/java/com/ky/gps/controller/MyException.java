package com.ky.gps.controller;

import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.util.ResultWrapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cyan-bw
 * @author Daye
 */
@ControllerAdvice
public class MyException {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyException.class);

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResultWrapper localException(Exception ex,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        LOGGER.error(ex.getMessage(), ex);
        response.setStatus(500);
        ResultWrapper resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR, ex.getMessage());
        return resultWrapper;
    }
}
