package io.colligence.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrefixedLogger {
	private String prefix;
	private final Logger logger;
	private static boolean verboseMode = false;

	public static void setVerboseMode() {
		verboseMode = true;
	}

	public static PrefixedLogger getLogger(Class<?> clazz) {
		return new PrefixedLogger(clazz.getSimpleName(), LoggerFactory.getLogger(clazz));
	}

	public static PrefixedLogger getLogger(String prefix, Class<?> clazz) {
		return new PrefixedLogger(prefix, LoggerFactory.getLogger(clazz));
	}

	public static PrefixedLogger getLogger(String name) {
		return new PrefixedLogger(name, LoggerFactory.getLogger(name));
	}

	public static PrefixedLogger getLogger(String prefix, String name) {
		return new PrefixedLogger(prefix, LoggerFactory.getLogger(name));
	}

	private PrefixedLogger(String prefix, Logger logger) {
		setPrefix(prefix);
		this.logger = logger;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix + " : ";
	}

	public void error(String pattern, Object... args) {
		logger.error(prefix.concat(pattern), args);
	}

	public void warn(String pattern, Object... args) {
		logger.warn(prefix.concat(pattern), args);
	}

	public void info(String pattern, Object... args) {
		logger.info(prefix.concat(pattern), args);
	}

	public void debug(String pattern, Object... args) {
		logger.debug(prefix.concat(pattern), args);
	}

	public void trace(String pattern, Object... args) {
		logger.trace(prefix.concat(pattern), args);
	}

	public void verbose(String pattern, Object... args) {
		if(verboseMode)
			trace(pattern, args);
	}

	public void exception(Throwable e, String message, Object... args) {
		StringBuilder sb = new StringBuilder();

		if(message == null) {
			sb.append("Exception ");
		} else {
			sb.append(message).append(" | Exception");
		}

		sb.append(e.getClass().getName()).append(" : ").append(e.getMessage());
		if(verboseMode) {
			for(StackTraceElement ste : e.getStackTrace())
				sb.append("\n").append(ste.toString());
		} else {
			for(StackTraceElement ste : e.getStackTrace()) {
				if(ste.toString().startsWith("com.axgate.overseer")) {
					sb.append(" @[").append(ste.toString()).append("]");
					break;
				}
			}
		}
		logger.error(sb.toString(), args);
	}

	public void exception(Throwable e) {
		exception(e, null);
	}

	public void logObjectAsJSON(Object o) {
		try {
			debug(JSONWriter.toJsonString(o));
		} catch(Exception e) {
			exception(e);
			e.printStackTrace();
		}
	}
}
