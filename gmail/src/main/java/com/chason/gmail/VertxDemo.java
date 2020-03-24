package com.chason.gmail;

import io.vertx.core.Vertx;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailConfig;
import io.vertx.ext.mail.MailMessage;
import io.vertx.ext.mail.StartTLSOptions;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2020/3/21
 */
public class VertxDemo {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        MailConfig kaadasConfig = new MailConfig();
        kaadasConfig.setHostname("smtp.gmail.com");
        kaadasConfig.setPort(465);
        kaadasConfig.setStarttls(StartTLSOptions.DISABLED);
        kaadasConfig.setSsl(true);
        kaadasConfig.setUsername("alphabkkthailand@gmail.com");
        kaadasConfig.setPassword("aht2015##");
        kaadasConfig.setMaxPoolSize(100);
        System.out.println("111111111111111111111111");
        MailClient kaadasClient = MailClient.createNonShared(vertx, kaadasConfig);
        System.out.println("22222222222222222222");
        MailMessage mailMessage = new MailMessage().setTo(args[0])
                .setFrom("alphabkkthailand@gmail.com")
                .setText("Dear user:\\n\\nYour verification code is 123456 and valid in 5 minutes. Please use it right away.\\n\\nThank you for selecting Kaadas\\n\\nKaadas Team")
                .setSubject("alpha e-mail verification code");
        System.out.println("3333333333333333333");

        kaadasClient.sendMail(mailMessage , rs -> {
            if (rs.failed())
                System.out.println(rs.cause());
            else
                System.out.println(">>>>kaadas send email success emailAddr -> chason777777@163.com");
        });
    }
}
