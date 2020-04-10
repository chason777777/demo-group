package handler.send;

import client.MailClient;
import io.vertx.core.Vertx;
import io.vertx.ext.mail.MailMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2020/4/8
 */
public class Send {
    private static Logger logger = LogManager.getLogger(Send.class);
    private Vertx vertx;
    private static String TO = "294381926@qq.com";
    private static String FROM = "lichao@kaadas.com";
    private static String SUBJECT = "test";



    public Send(Vertx vertx){
        this.vertx = vertx;
    }

    public void start(){
        MailClient.alphaClient.sendMail(new MailMessage().setTo(TO)
                .setFrom(FROM)
                .setText("6666666666")
                .setSubject(SUBJECT), rs -> {
            if (rs.failed())
                logger.error("alpha send fail email -> {} , exception -> {} ",
                        TO, rs.cause().getMessage());
            else
                logger.info(">>>>alpha send email success emailAddr -> " + TO);
        });
    }
}
