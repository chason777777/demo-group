package verticel;

import client.MailClient;
import handler.send.Send;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2020/4/8
 */
public class EmailVerticel extends AbstractVerticle {
    private static Logger logger = LogManager.getLogger(AbstractVerticle.class);

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        MailClient mailClient = new MailClient();
        mailClient.mailConf(vertx);

        Send s = new Send(vertx);
        s.start();
        startFuture.complete();
    }
}
