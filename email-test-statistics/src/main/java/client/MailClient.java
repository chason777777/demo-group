package client;

import io.vertx.core.Vertx;
import io.vertx.ext.mail.MailConfig;
import io.vertx.ext.mail.StartTLSOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author zhang bo
 * @version 1.0
 * @Description
 * @date 2017-12-12
 */
public class MailClient {
    public static io.vertx.ext.mail.MailClient alphaClient;

    private static Logger logger = LogManager.getLogger(MailClient.class);
    /**
     * r
     *
     * @Description 配置邮箱服务
     * @author zhang bo
     * @date 17-11-23
     * @version 1.0
     */
    public void mailConf(Vertx vertx) {
        InputStream mainIn = MailClient.class.getResourceAsStream("/email-config.properties");
        try {
            Properties mainConfig = new Properties();
            mainConfig.load(mainIn);

            MailConfig alphaConfig = new MailConfig();
            alphaConfig.setHostname(mainConfig.getProperty("kaadasmailServerHost"));
            alphaConfig.setPort(Integer.parseInt(mainConfig.getProperty("kaadasmailServerPort")));
            alphaConfig.setUsername(mainConfig.getProperty("kaadasuserName"));
            alphaConfig.setPassword(mainConfig.getProperty("kaadaspassword"));
            alphaConfig.setMaxPoolSize(Integer.parseInt(mainConfig.getProperty("maxPool")));
//            alphaConfig.setHostname(mainConfig.getProperty("alphamailServerHost"));
//            alphaConfig.setPort(Integer.parseInt(mainConfig.getProperty("alphamailServerPort")));
//            alphaConfig.setUsername(mainConfig.getProperty("alphauserName"));
//            alphaConfig.setPassword(mainConfig.getProperty("alphapassword"));
//            alphaConfig.setMaxPoolSize(Integer.parseInt(mainConfig.getProperty("maxPool")));
            alphaConfig.setStarttls(StartTLSOptions.DISABLED);
            alphaConfig.setSsl(true);
            alphaClient = io.vertx.ext.mail.MailClient.createNonShared(vertx, alphaConfig);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
