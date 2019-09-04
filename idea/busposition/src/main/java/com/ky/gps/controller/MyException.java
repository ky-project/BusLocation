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

/**
 * @author cyan-bw
 */
@ControllerAdvice
public class MyException {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyException.class);

	@ExceptionHandler({Exception.class})
	@ResponseBody
	public ResultWrapper localException(Exception ex, HttpServletRequest request){
		LOGGER.error(ex.getMessage(), ex);
		ResultWrapper resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
		return resultWrapper;
	}
}
