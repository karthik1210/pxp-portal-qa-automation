package com.pxp.setup;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.Reporter;

public class Log4jUtil {

        static Logger logger = Logger.getLogger(Log4jUtil.class);

        public Log4jUtil() {
        }

        public static Level getLogLevel() {
            String _level = TestConfig.getLoggerLevel();
            return _level.equalsIgnoreCase("debug") ? Level.DEBUG : Level.INFO;
        }

        public static void log(String message) {
            logger.info(message);
            Reporter.log(message + "<br/>", false);
        }

        public static void log(String message, Level lv) {
            Level _lv = getLogLevel();
            if (_lv.toInt() <= lv.toInt()) {
                if (lv == Level.INFO) {
                    logger.info(message);
                    Reporter.log(message + "<br/>", false);
                }

                if (lv == Level.DEBUG) {
                    logger.debug(message);
                    Reporter.log(message + "<br/>", false);
                }

                if (lv == Level.ERROR) {
                    logger.error(message);
                    Reporter.log(message + "<br/>", false);
                }
            }

        }

        public static void log(String message, String lv) {
            if (lv.equalsIgnoreCase("debug")) {
                log(message, Level.DEBUG);
            } else if (lv.equalsIgnoreCase("error")) {
                log(message, Level.ERROR);
            } else {
                log(message, Level.INFO);
            }

        }
    }
