package org.lnson.util;

import java.util.UUID;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public final class Log4jUtils {

	private Logger _logger;

	public Log4jUtils(String logConfig) {
		PropertyConfigurator.configureAndWatch(logConfig, 10000);
		_logger = LogManager
				.getLogger(String.join("", UUID.randomUUID().toString().split("-")) + System.currentTimeMillis());
	}

	public static Log4jUtils getLog4jUtil() {
		return new Log4jUtils("log4j.properties");
	}

	public void debug(Object message) {
		_logger.debug(message);
	}

	public void info(Object message) {
		_logger.info(message);
	}

	public void warn(Object message) {
		_logger.warn(message);
	}

	public void error(Object message) {
		_logger.error(message);
	}

	public void fatal(Object message) {
		_logger.fatal(message);
	}

}
