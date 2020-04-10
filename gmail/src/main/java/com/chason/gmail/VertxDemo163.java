package com.chason.gmail;

import io.vertx.core.Vertx;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailConfig;
import io.vertx.ext.mail.MailMessage;
import io.vertx.ext.mail.StartTLSOptions;

import java.util.Date;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2020/3/21
 */
public class VertxDemo163 {
    public static void main(String[] args) throws Exception {
        Vertx vertx = Vertx.vertx();
        MailConfig alphaConfig = new MailConfig();
        alphaConfig.setHostname("smtp.gmail.com");
        alphaConfig.setPort(465);
        alphaConfig.setStarttls(StartTLSOptions.DISABLED);
        alphaConfig.setSsl(true);
//        alphaConfig.setUsername("chason777777@gmail.com");
//        alphaConfig.setPassword("lichao256808");
        alphaConfig.setUsername("kdstest0002@gmail.com");
        alphaConfig.setPassword("kaadas123");
        alphaConfig.setMaxPoolSize(1);

//        MailClient alphaClient = MailClient.createNonShared(vertx, alphaConfig);
//        System.out.println("开始发送邮件，from -> chason777777@gmail.com, to -> kdstest001@163.com");
//        System.out.println("开始发送邮件，from -> kdstest0002@gmail.com, to -> kdstest001@163.com");
//        for (int i =1;i<=500;i++){
//            Thread.sleep(5000L);
//            System.out.println("正在发送第" + i + "封邮件");
//            MailMessage mailMessage = new MailMessage().setTo("kdstest001@163.com")
//                    .setFrom("chason777777@gmail.com")
//                    .setText("text" + new Date())
//                    .setSubject("subject");
//
//            alphaClient.sendMail(mailMessage , rs -> {
//                if (rs.failed()) {
//                    System.out.println(rs.cause());
//                }
//            });
//
//        }
//        System.out.println("开始发送邮件，from -> chason777777@gmail.com, to -> kdstest0001@gmail.com");
//        System.out.println("开始发送邮件，from -> kdstest0002@gmail.com, to -> kdstest0001@gmail.com");
//        for (int i =1;i<=500;i++){
//            Thread.sleep(5000L);
//            System.out.println("正在发送第" + i + "封邮件");
//            MailMessage mailMessage = new MailMessage().setTo("kdstest0001@gmail.com")
//                    .setFrom("chason777777@gmail.com")
//                    .setText("text" + new Date())
//                    .setSubject("subject");
//
//            alphaClient.sendMail(mailMessage , rs -> {
//                if (rs.failed())
//                    System.out.println(rs.cause());
//            });
//        }
//        System.out.println("开始发送邮件，from -> chason777777@gmail.com, to -> 337522212@qq.com");
//        System.out.println("开始发送邮件，from -> kdstest0002@gmail.com, to -> 337522212@qq.com");
//        for (int i = 1; i <= 500; i++) {
//            Thread.sleep(5000L);
//            System.out.println("正在发送第" + i + "封邮件");
//            MailMessage mailMessage = new MailMessage().setTo("337522212@qq.com")
//                    .setFrom("chason777777@gmail.com")
//                    .setText("text" + new Date())
//                    .setSubject("subject");
//
//            alphaClient.sendMail(mailMessage, rs -> {
//                if (rs.failed()) {
//                    System.out.println(rs.cause());
//                }
//            });

//        }

        MailConfig kaadasConfig = new MailConfig();
        kaadasConfig.setHostname("smtp.163.com");
        kaadasConfig.setPort(465);
        kaadasConfig.setStarttls(StartTLSOptions.DISABLED);
        kaadasConfig.setSsl(true);
//        kaadasConfig.setUsername("kdstest001@163.com");
//        kaadasConfig.setPassword("IQNVQQMFXVFPPUTI");
        kaadasConfig.setUsername("kdstest002@163.com");
        kaadasConfig.setPassword("VXZKANKCJTEEDRLK");
        kaadasConfig.setMaxPoolSize(1);
        MailClient kaadasClient = MailClient.createNonShared(vertx, kaadasConfig);
//        System.out.println("开始发送邮件，from -> kdstest001@163.com, to -> kdstest002@163.com");
//        for (int i =1;i<=500;i++){
//            Thread.sleep(5000L);
//            System.out.println("正在发送第" + i + "封邮件");
//            MailMessage mailMessage = new MailMessage().setTo("kdstest002@163.com")
//                    .setFrom("kdstest001@163.com")
//                    .setText("text" + new Date())
//                    .setSubject("subject");
//
//            kaadasClient.sendMail(mailMessage , rs -> {
//                if (rs.failed()) {
//                    System.out.println(rs.cause());
//                }
//            });
//
//        }
//        System.out.println("开始发送邮件，from -> kdstest001@163.com, to -> chason777777@gmail.com");
        System.out.println("开始发送邮件，from -> kdstest002@163.com, to -> chason777777@gmail.com");
        for (int i =1;i<=500;i++){
            Thread.sleep(5000L);
            System.out.println("正在发送第" + i + "封邮件");
            MailMessage mailMessage = new MailMessage().setTo("chason777777@gmail.com")
                    .setFrom("kdstest002@163.com")
                    .setText("kds test,time:" + new Date())
                    .setSubject("subject");

            kaadasClient.sendMail(mailMessage , rs -> {
                if (rs.failed())
                    System.out.println(rs.cause());
            });
        }
    }
}
