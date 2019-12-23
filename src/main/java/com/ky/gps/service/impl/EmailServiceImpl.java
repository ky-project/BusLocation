package com.ky.gps.service.impl;

import com.ky.gps.service.EmailService;
import com.ky.gps.util.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 邮件相关service类-实现类
 *
 * @author Darren
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail(String email, String checkCode) {
        try {
            SendMail.sendEmil(email, checkCode);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
