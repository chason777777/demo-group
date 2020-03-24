package cn.chason678.mqttClientPt.log;

import io.vertx.core.eventbus.Message;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2019/6/5
 */
public class Log4jHandler {
    private static Logger logger = LogManager.getLogger(Log4jHandler.class);

    /**
     * 修改日志级别
     *
     * @param message
     */
    public void updateLogLevel(Message<String> message) {
        String level = message.body();

        logger.info("update log level -> {}", level);
        switch (level) {
            case "all":
                Configurator.setRootLevel(Level.ALL);
                break;
            case "info":
                Configurator.setRootLevel(Level.INFO);
                break;
            case "error":
                Configurator.setRootLevel(Level.ERROR);
                break;
            case "debug":
                Configurator.setRootLevel(Level.DEBUG);
                break;
        }
    }
}
