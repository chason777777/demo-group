package com.example.springemail.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2020/4/9
 */
@Service
public class Sender {
    //邮件的发送者
    @Value("${spring.mail.username}")
    private String from;

    //注入MailSender
    @Autowired
    private JavaMailSender mailSender;

    /**
     * @param title        邮件标题
     * @param to 收件人地址
     * @param receipt 是否需要回执
     * @throws Exception
     */
    public void sendMessageMail(String title,String content, String to, boolean receipt) throws Exception{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        if(receipt) {
//            mimeMessage.setHeader("Disposition-Notification-To", "1");
//        }
        mimeMessage.setContentID(System.currentTimeMillis()+"");
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("【" + title + "-" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "】");//邮件标题
        helper.setText(content, true);
        System.out.println(mimeMessage.getContentID());
        mailSender.send(helper.getMimeMessage());
    }
}
