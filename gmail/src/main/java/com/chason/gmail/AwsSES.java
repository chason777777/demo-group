package com.chason.gmail;

import io.vertx.core.Vertx;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailConfig;
import io.vertx.ext.mail.MailMessage;
import io.vertx.ext.mail.StartTLSOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2020/4/14
 */
public class AwsSES {
    public static void main(String[] args) throws Exception {
        Vertx vertx = Vertx.vertx();
        MailConfig alphaConfig = new MailConfig();
        // alpha
//        alphaConfig.setHostname("email-smtp.ap-southeast-2.amazonaws.com");
//        alphaConfig.setUsername("AKIAWQLZEMZ42MXHUBBD");
//        alphaConfig.setPassword("BExixhxqMrgmY6uaYhCtVrIvdsZCthVZBrggUA8KsgZo");
        // kaadas
        alphaConfig.setHostname("email-smtp.us-west-2.amazonaws.com");
        alphaConfig.setUsername("AKIA33LTG4TJIUMHIII5");
        alphaConfig.setPassword("BFDX8MTJfkUfHouQcFWfnt38m3B5DMLe3V4gu67u51qu");
        alphaConfig.setPort(465);
        alphaConfig.setStarttls(StartTLSOptions.DISABLED);
        alphaConfig.setSsl(true);
        alphaConfig.setMaxPoolSize(1);

        List<String> toList = new ArrayList<>();
        toList.add("kdstest002@163.com");
        toList.add("kdstest0001@gmail.com");
        toList.add("337522212@qq.com");
        toList.add("kdstest001@outlook.com");
        toList.add("kdstest001@aol.com");
        toList.add("kdstest001@yahoo.com");
        MailClient alphaClient = MailClient.createNonShared(vertx, alphaConfig);

        int total = 50;
        if (args.length > 0) {
            total = Integer.valueOf(args[0]);
        }
        System.out.println("邮件数量 -> " + total);
        System.out.println("---------------------------start------------------------");

        for (int i = 1; i <= total; i++) {
            for (String to : toList) {
                System.out.println("正在发送第" + i + "封邮件, To -> " + to);
                MailMessage mailMessage = new MailMessage().setTo(to)
                        .setFrom("kdstest001@163.com")
                        .setText("Dear user:\n\nYour verification code is 123123 and valid in 5 minutes. Please use it right away.\n\nThank you for selecting Alpha\n\nAlpha")
                        .setSubject("Alpha e-mail verification code");

                alphaClient.sendMail(mailMessage, rs -> {
                    if (rs.failed()) {
                        System.out.println(rs.cause());
                    }
                });
            }
            Thread.sleep(2000);
        }
        System.out.println("---------------------------end------------------------");
    }
}
