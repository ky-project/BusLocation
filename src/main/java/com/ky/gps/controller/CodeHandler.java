package com.ky.gps.controller;

import com.ky.gps.util.DrawImgUtil;
import org.apache.shiro.web.session.HttpServletSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 验证码等code信息验证接口
 *
 * @author Daye
 */
@Controller
@RequestMapping(value = "/code")
public class CodeHandler {
    /**
     * 日志打印对象
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(CodeHandler.class);

    /**
     * 随机生成5位随机验证码
     * 验证码存入session
     *
     * @param response 响应域
     * @param session 请求域
     * @throws IOException io异常
     */
    @RequestMapping(value = "/getVerifyCode", method = RequestMethod.GET)
    public void createVerifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        //创建字节流
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        //获取随机产生的字符串
        String verifyCodeValue = DrawImgUtil.getInstance().drawImg(output);
        //日志打印
        LOGGER.debug("产生随机数" + verifyCodeValue);
        //将随机串存入session
        session.setAttribute("verifyCodeValue", verifyCodeValue);
        //获取响应流
        ServletOutputStream out = response.getOutputStream();
        //写入到响应流
        output.writeTo(out);
    }
}
